package com.invia.interview.cashpoolapplication.services;

import com.invia.interview.cashpoolapplication.models.dto.TravellerDto;

public interface TravellerService {

    TravellerDto addTraveller(TravellerDto travellerDto, String tripCode);
    TravellerDto excludeTraveller(TravellerDto travellerDto, String tripCode);
    TravellerDto getTravellerDtoById(long id);

}
