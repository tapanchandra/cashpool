package com.invia.interview.cashpoolapplication.models.converters;

import com.invia.interview.cashpoolapplication.models.Traveller;
import com.invia.interview.cashpoolapplication.models.dto.TravellerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravellerDtoConverter implements Converter<Traveller,TravellerDto> {
    @Override
    public TravellerDto convert(Traveller traveller) {

        return TravellerDto.builder()
                .id(traveller.getId())
                .email(traveller.getEmail())
                .name(traveller.getName())
                .build();
    }

    public Traveller convert(TravellerDto travellerDto) {

        Traveller traveller = new Traveller();
        traveller.setId(travellerDto.getId());
        traveller.setEmail(travellerDto.getEmail());
        traveller.setName(travellerDto.getName());

        return traveller;
    }

    public List<TravellerDto> convert(Collection<Traveller> travellers) {
        return travellers.stream().map(traveller -> this.convert(traveller)).collect(Collectors.toList());
    }
}
