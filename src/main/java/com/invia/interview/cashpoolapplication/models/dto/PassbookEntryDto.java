package com.invia.interview.cashpoolapplication.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class PassbookEntryDto {

    private Date creationDate;

    private TravellerDto payer;

    private TravellerDto receiver;

    private double amount;
}
