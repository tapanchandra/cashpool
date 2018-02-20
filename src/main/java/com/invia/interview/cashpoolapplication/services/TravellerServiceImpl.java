package com.invia.interview.cashpoolapplication.services;

import com.invia.interview.cashpoolapplication.exceptions.TripNotFoundException;
import com.invia.interview.cashpoolapplication.models.Traveller;
import com.invia.interview.cashpoolapplication.models.Trip;
import com.invia.interview.cashpoolapplication.repositories.TravellerRepository;
import com.invia.interview.cashpoolapplication.repositories.TripRepository;
import com.invia.interview.cashpoolapplication.models.converters.TravellerDtoConverter;
import com.invia.interview.cashpoolapplication.models.dto.TravellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class TravellerServiceImpl implements TravellerService {

    @Autowired TravellerRepository travellerRepository;
    @Autowired TripRepository tripRepository;
    @Autowired TravellerDtoConverter travellerDtoConverter;

    @Override
    public TravellerDto addTraveller(TravellerDto travellerDto,String tripCode) {

        Optional<Traveller> travellerOptional = travellerRepository.findByEmail(travellerDto.getEmail());

        if(!travellerOptional.isPresent()) {

            Traveller traveller = travellerDtoConverter.convert(travellerDto);
            traveller = travellerRepository.save(traveller);
            travellerOptional = Optional.of(traveller);
        }

        Trip trip = tripRepository.findByTripCode(tripCode).orElseThrow(
                () -> new TripNotFoundException(tripCode)
        );
        Traveller traveller = travellerOptional.get();
        trip.getTravellers().add(traveller);
        tripRepository.save(trip);

        travellerDto.setId(traveller.getId());
        return travellerDto;

    }

    @Override
    public TravellerDto excludeTraveller(TravellerDto travellerDto, String tripCode) {
        return null;
    }

    @Override
    public TravellerDto getTravellerDtoById(long id) {
        Traveller traveller = travellerRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(MessageFormat.format("Could not find traveller with Id {0}",id))
                );
        return travellerDtoConverter.convert(traveller);
    }
}
