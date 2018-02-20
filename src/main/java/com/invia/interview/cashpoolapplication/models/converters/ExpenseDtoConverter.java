package com.invia.interview.cashpoolapplication.models.converters;

import com.invia.interview.cashpoolapplication.models.Expense;
import com.invia.interview.cashpoolapplication.models.dto.ExpenseDto;
import com.invia.interview.cashpoolapplication.models.dto.TripIdentificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseDtoConverter implements Converter<Expense,ExpenseDto> {

    @Autowired
    private TravellerDtoConverter travellerDtoConverter;

    @Override
    public ExpenseDto convert(Expense expense) {
        return ExpenseDto.builder()
                .paidBy(travellerDtoConverter.convert(expense.getPaidBy()))
                .expenseDescription(expense.getExpenseDescription())
                .paidFor(travellerDtoConverter.convert(expense.getPaidForAll()))
                .amount(expense.getAmount())
                .trip(new TripIdentificationDto(expense.getTrip().getTripCode(),expense.getTrip().getTripName()))
                .build();
    }

    public List<ExpenseDto> convert(Collection<Expense> expenses) {
        return expenses.stream().map(expense -> this.convert(expense)).collect(Collectors.toList());
    }

    public Expense convert(ExpenseDto expense) {
        return Expense.builder()
                .paidBy(travellerDtoConverter.convert(expense.getPaidBy()))
                .expenseDescription(expense.getExpenseDescription())
                .id(expense.getId())
                .amount(expense.getAmount())
                .paidForAll(expense.getPaidFor().stream().map(travellerDto -> travellerDtoConverter.convert(travellerDto)).collect(Collectors.toList()))
                .build();
    }
}
