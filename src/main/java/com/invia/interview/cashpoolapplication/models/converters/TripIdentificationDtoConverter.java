package com.invia.interview.cashpoolapplication.models.converters;

import com.invia.interview.cashpoolapplication.models.Trip;
import com.invia.interview.cashpoolapplication.models.dto.TripIdentificationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TripIdentificationDtoConverter implements Converter<Trip,TripIdentificationDto> {


    @Override
    public TripIdentificationDto convert(Trip trip) {
        return TripIdentificationDto.builder()
                .tripCode(trip.getTripCode())
                .tripName(trip.getTripName())
                .build();
    }
}
