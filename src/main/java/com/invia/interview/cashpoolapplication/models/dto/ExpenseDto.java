package com.invia.interview.cashpoolapplication.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ExpenseDto {

    private long id;

    private TripIdentificationDto trip;

    private TravellerDto paidBy;

    private List<TravellerDto> paidFor;

    private String expenseDescription;

    private double amount;
}
