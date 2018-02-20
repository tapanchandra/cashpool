package com.invia.interview.cashpoolapplication.services;

import com.invia.interview.cashpoolapplication.models.dto.ExpenseDto;
import com.invia.interview.cashpoolapplication.models.dto.TripDto;
import com.invia.interview.cashpoolapplication.models.dto.TripIdentificationDto;

public interface TripService {

    String generateNewTripCode(Long tripId);
    TripIdentificationDto createNewTrip();
    TripDto getTripDtoByCode(String tripCode);
    TripIdentificationDto getTripIdentificationDtoByCode(String tripCode);
    TripDto startTrip(String tripCode);
    TripDto finishTrip(String tripCode);

    void updateTrip(TripDto tripDto);

    ExpenseDto addExpense(ExpenseDto expenseDto, String tripCode);
}
