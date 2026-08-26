package com.mccr.rutalibre.domain.exception;

public class VehicleWithPlateExistsException extends RuntimeException {
    public VehicleWithPlateExistsException(String message) {
        super(message);
    }
}
