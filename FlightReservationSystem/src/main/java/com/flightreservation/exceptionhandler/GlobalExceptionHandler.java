package com.flightreservation.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.flightreservation.exceptions.ErrorResponse;
import com.flightreservation.exceptions.FlightApplicationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FlightApplicationException.class)
    public ResponseEntity<Object> handleSeatBookingException(FlightApplicationException ex, WebRequest request) {
    	ErrorResponse errorResponse = null;
    	switch(ex.getMessage()) {
        case "Username cannot contain symbols like [^`~|#{}=+-_]":
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        case "Username should be within 6 to 12 characters":
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        case "Password cannot contain symbols like [^`~|#{}=+-_]":
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        case "Password should be within 6 to 12 characters":
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    	}
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
