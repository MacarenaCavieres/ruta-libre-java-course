package com.mccr.rutalibre.domain.exception;

public class BookingNotCreatedException extends RuntimeException {
    public BookingNotCreatedException(String message) {
        super(message);
    }
}
