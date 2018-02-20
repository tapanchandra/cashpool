package com.invia.interview.cashpoolapplication.models.converters;

import com.invia.interview.cashpoolapplication.models.Trip;
import com.invia.interview.cashpoolapplication.models.dto.TripDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TripDtoConverter implements Converter<Trip,TripDto> {

    @Autowired
    private TravellerDtoConverter travellerDtoConverter;

    @Autowired
    private ExpenseDtoConverter expenseDtoConverter;

    @Autowired
    private PassbookEntryDtoConverter passbookEntryDtoConverter;

    @Override
    public TripDto convert(Trip trip) {
        return TripDto.builder()
                .tripCode(trip.getTripCode())
                .createdDate(trip.getCreatedDate())
                .tripName(trip.getTripName())
                .tripLocation(trip.getLocation())
                .tripDescription(trip.getTripDescription())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .tripStatus(trip.getStatus())
                .travellers(travellerDtoConverter.convert(trip.getTravellers()))
                .expenses(expenseDtoConverter.convert(trip.getExpenses()))
                .passbookEntries(passbookEntryDtoConverter.convert(trip.getPassbook().getPassbookEntries()))
                .build();
    }
}
