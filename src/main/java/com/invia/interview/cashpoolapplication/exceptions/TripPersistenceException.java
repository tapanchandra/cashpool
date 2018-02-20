package com.invia.interview.cashpoolapplication.exceptions;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.PersistenceException;

public class TripPersistenceException extends PersistenceException {

    @Getter @Setter private Long tripId;

    public TripPersistenceException(Long id,String message,Throwable cause) {
        super(message,cause);
        this.tripId = id;
    }
}
