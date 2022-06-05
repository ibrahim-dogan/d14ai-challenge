package com.example.d14aichallenge.exceptions.handlers;

import com.example.d14aichallenge.exceptions.CitizenNotFoundException;
import com.example.d14aichallenge.models.http.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CitizenNotFoundAdvice {

    @ExceptionHandler(CitizenNotFoundException.class)
    ResponseEntity<ErrorResponse> handle(CitizenNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
