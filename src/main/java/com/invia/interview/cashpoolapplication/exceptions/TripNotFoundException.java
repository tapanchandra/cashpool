package com.invia.interview.cashpoolapplication.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)  // 404
@RequiredArgsConstructor
public class TripNotFoundException extends RuntimeException {

    @Getter private final String tripCode;

}
