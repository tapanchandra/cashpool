package com.invia.interview.cashpoolapplication.controllers;

import com.invia.interview.cashpoolapplication.configuration.Constants;
import com.invia.interview.cashpoolapplication.exceptions.TripCodeEmptyException;
import com.invia.interview.cashpoolapplication.exceptions.TripNotFoundException;
import com.invia.interview.cashpoolapplication.exceptions.TripPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;


@ControllerAdvice(basePackages = "com.invia.interview.cashpoolapplication")
public class GlobalExceptionErrorMappingController {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionErrorMappingController.class);

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTripNotFoundException(TripNotFoundException tripNotFoundException,final Model model) {
        logger.debug(tripNotFoundException.getMessage(),tripNotFoundException);
        model.addAttribute("errorTitle", Constants.TRIP_NOT_FOUND_ERROR_TITLE);
        model.addAttribute("errorMessage", MessageFormat.format(Constants.TRIP_NOT_FOUND_ERROR_MESSAGE,tripNotFoundException.getTripCode()));
        return "errorhandlers/error";
    }

    @ExceptionHandler(TripCodeEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTripCodeEmptyException(TripCodeEmptyException tripCodeEmptyException ,final Model model) {
        logger.debug(tripCodeEmptyException.getMessage(),tripCodeEmptyException);
        model.addAttribute("errorTitle", Constants.TRIP_CODE_EMPTY_ERROR_TITLE);
        model.addAttribute("errorMessage", Constants.TRIP_CODE_EMPTY_ERROR_MESSAGE);
        return "errorhandlers/error";
    }

    @ExceptionHandler(TripPersistenceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTripPersistenceException(TripPersistenceException tripPersistenceException , final Model model) {
        logger.debug(tripPersistenceException.getMessage(),tripPersistenceException);
        model.addAttribute("errorTitle", Constants.TRIP_PERSISTENCE_ERROR_TITLE);
        model.addAttribute("errorMessage", Constants.TRIP_PERSISTENCE_ERROR_MESSAGE);
        return "errorhandlers/error";
    }
}