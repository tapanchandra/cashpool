package com.invia.interview.cashpoolapplication.services;

import com.invia.interview.cashpoolapplication.configuration.Constants;
import com.invia.interview.cashpoolapplication.exceptions.TripNotFoundException;
import com.invia.interview.cashpoolapplication.exceptions.TripPersistenceException;
import com.invia.interview.cashpoolapplication.models.*;
import com.invia.interview.cashpoolapplication.repositories.TravellerRepository;
import com.invia.interview.cashpoolapplication.repositories.TripRepository;
import com.invia.interview.cashpoolapplication.models.converters.ExpenseDtoConverter;
import com.invia.interview.cashpoolapplication.models.converters.TravellerDtoConverter;
import com.invia.interview.cashpoolapplication.models.converters.TripDtoConverter;
import com.invia.interview.cashpoolapplication.models.converters.TripIdentificationDtoConverter;
import com.invia.interview.cashpoolapplication.models.dto.ExpenseDto;
import com.invia.interview.cashpoolapplication.models.dto.TripDto;
import com.invia.interview.cashpoolapplication.models.dto.TripIdentificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private static Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);
    public static final String ALPHABET = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    public static final int BASE = ALPHABET.length();

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TravellerRepository travellerRepository;

    @Autowired @Lazy
    TripIdentificationDtoConverter tripIdentificationDtoConverter;

    @Autowired @Lazy
    TripDtoConverter tripDTOConverter;

    @Autowired @Lazy
    TravellerDtoConverter travellerDtoConverter;

    @Autowired @Lazy
    ExpenseDtoConverter expenseDtoConverter;

    @Override
    public TripIdentificationDto createNewTrip() {
        Trip savedTrip = Trip.builder().tripName("Alohá").status(Trip.Status.STARTED).location("hawaii").passbook(Passbook.builder().build()).build();

        try {
            //Start a transaction here.
            savedTrip = tripRepository.save(savedTrip);
            savedTrip.setTripCode(this.generateNewTripCode(savedTrip.getId()));
            tripRepository.save(savedTrip);
            //End transaction
            logger.debug("Trip instance has been persisted - {}",savedTrip);
        } catch (RuntimeException ex) {
            throw new TripPersistenceException(savedTrip.getId(),"Error while persisting Trip instance",ex);
        }

        logger.info("New trip generation complete. - " + savedTrip.getTripCode());
        return tripIdentificationDtoConverter.convert(savedTrip);
    }

    @Override
    public String generateNewTripCode(Long tripId) {

        if(tripId < 0 || tripId > Long.MAX_VALUE)
            throw new IllegalArgumentException("Trip Id should be a valid positive long number");

        StringBuilder shortCodeBuilder = new StringBuilder();
        while (tripId > 0) {
            int charIndex = (int)tripId.longValue() % BASE;
            shortCodeBuilder.insert(0, ALPHABET.charAt(charIndex));
            tripId = tripId / BASE;
        }
        return shortCodeBuilder.toString();
    }

    @Override
    public TripDto getTripDtoByCode(String tripCode) throws TripNotFoundException {
        Trip trip = getTripByCode(tripCode);
        return tripDTOConverter.convert(trip);
    }

    @Override
    public TripIdentificationDto getTripIdentificationDtoByCode(String tripCode) {
        Trip trip = getTripByCode(tripCode);
        return tripIdentificationDtoConverter.convert(trip);
    }

    @Override
    public TripDto startTrip(String tripCode) {
        Trip trip = updateTripStatus(tripCode, Trip.Status.STARTED);
        return tripDTOConverter.convert(trip);
    }

    @Override
    @Transactional
    public TripDto finishTrip(String tripCode) {

        //Update status
        Trip trip = updateTripStatus(tripCode, Trip.Status.FINISHED);

        //Create Passbook
        trip = generatePassbook(trip);

        return tripDTOConverter.convert(trip);
    }

    public Trip generatePassbook(Trip trip) {

        @Data
        @AllArgsConstructor
        class TravellerReceivables {
            Traveller traveller;
            double amount;
        }

        Map<Traveller,TravellerReceivables> calculatedReceivableByTraveller = new HashMap<>();

        //Process Receivable amounts for each traveller
        Map<Traveller,List<Expense>> groupedExpenses = trip.getExpenses().stream().collect(Collectors.groupingBy(expense -> expense.getPaidBy()));
        groupedExpenses.entrySet().forEach(entry -> {
            double receivable = entry.getValue().stream().mapToDouble(Expense::getAmount).sum();
            calculatedReceivableByTraveller.put(entry.getKey(),new TravellerReceivables(entry.getKey(),receivable));
        });

        //Process Payable amounts for each traveller
        trip.getExpenses().forEach(expense -> {
            double amount = expense.getAmount();
            double individualShareAmount = amount / expense.getPaidForAll().size();

            expense.getPaidForAll().stream().forEach(traveller -> {
                double currentReceivable = calculatedReceivableByTraveller.containsKey(traveller)?calculatedReceivableByTraveller.get(traveller).getAmount():0.0;
                calculatedReceivableByTraveller.put(traveller,new TravellerReceivables(traveller,currentReceivable - individualShareAmount));
            });
        });

        //Generate Passbook entries
        List<TravellerReceivables> payingTravellers = calculatedReceivableByTraveller.values().stream().filter(travellerReceivables -> travellerReceivables.amount < 0).collect(Collectors.toList());
        List<TravellerReceivables> receivingTravellers = calculatedReceivableByTraveller.values().stream().filter(travellerReceivables -> travellerReceivables.amount > 0).collect(Collectors.toList());

        payingTravellers.sort(Comparator.comparingDouble(TravellerReceivables::getAmount));
        receivingTravellers.sort(Comparator.comparingDouble(TravellerReceivables::getAmount).reversed());


        //Calculate Passbook Entries
        while (receivingTravellers.stream().mapToDouble(TravellerReceivables::getAmount).sum() >= Constants.EPSILON) {

            TravellerReceivables payer = payingTravellers.stream().filter(tr -> Math.abs(tr.getAmount()) > 0).findFirst().get();
            TravellerReceivables receiver = receivingTravellers.stream().filter(tr -> Math.abs(tr.getAmount()) > 0).findFirst().get();
            double transferAmount = Math.abs(Math.abs(payer.getAmount()) <= Math.abs(receiver.getAmount()) ? payer.getAmount():receiver.getAmount());
            PassbookEntry newEntry = PassbookEntry.builder().payer(payer.getTraveller()).receiver(receiver.getTraveller()).amount(transferAmount).build();

            payer.setAmount(payer.getAmount() + transferAmount);
            receiver.setAmount(receiver.getAmount() - transferAmount);
            newEntry.setPassbook(trip.getPassbook());

            trip.getPassbook().getPassbookEntries().add(newEntry);
        }

        //Save Trip
        tripRepository.save(trip);
        return trip;
    }

    @Override
    public void updateTrip(TripDto tripDto) {
        Trip trip = getTripByCode(tripDto.getTripCode());
        trip.setTripName(tripDto.getTripName());
        trip.setLocation(tripDto.getTripLocation().isEmpty()?trip.getLocation():tripDto.getTripLocation());
        trip.setTripDescription(tripDto.getTripDescription());
        tripRepository.save(trip);

    }

    @Override
    public ExpenseDto addExpense(ExpenseDto expenseDto, String tripCode) {
        Trip trip = getTripByCode(tripCode);
        Expense expense = expenseDtoConverter.convert(expenseDto);
        trip.getExpenses().add(expense);
        expense.setTrip(trip);
        tripRepository.save(trip);

        return expenseDto;
    }

    private Trip getTripByCode(String tripCode) {
        Trip trip = tripRepository
                .findByTripCode(tripCode)
                .orElseThrow(
                        () -> new TripNotFoundException(tripCode)
                );
        return trip;

    }

    private Trip updateTripStatus(String tripCode,Trip.Status status) {
        Trip trip = getTripByCode(tripCode);
        trip.setStatus(status);
        tripRepository.save(trip);
        return trip;
    }
}
