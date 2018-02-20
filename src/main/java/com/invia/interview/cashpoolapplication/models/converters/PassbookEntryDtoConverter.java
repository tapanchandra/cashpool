package com.invia.interview.cashpoolapplication.models.converters;

import com.invia.interview.cashpoolapplication.models.PassbookEntry;
import com.invia.interview.cashpoolapplication.models.dto.PassbookEntryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassbookEntryDtoConverter implements Converter<PassbookEntry,PassbookEntryDto> {

    @Autowired
    TravellerDtoConverter travellerDtoConverter;

    @Override
    public PassbookEntryDto convert(PassbookEntry passbookEntry) {
        return PassbookEntryDto.builder()
                .creationDate(passbookEntry.getCreatedDate())
                .payer(travellerDtoConverter.convert(passbookEntry.getPayer()))
                .receiver(travellerDtoConverter.convert(passbookEntry.getReceiver()))
                .amount(passbookEntry.getAmount())
                .build();
    }

    public List<PassbookEntryDto> convert(Collection<PassbookEntry> passbookEntries) {
        return passbookEntries.stream().map(passbookEntry -> this.convert(passbookEntry)).collect(Collectors.toList());
    }
}
