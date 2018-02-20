package com.invia.interview.cashpoolapplication.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)  // 404
@NoArgsConstructor
public class TripCodeEmptyException extends RuntimeException{
}
