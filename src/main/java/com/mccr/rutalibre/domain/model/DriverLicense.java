package com.mccr.rutalibre.domain.model;

public record DriverLicense(
        String type,
        String expirationDate
) {

    public DriverLicense {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("License type cannot be blank");
        }

        if (expirationDate == null || expirationDate.isBlank()) {
            throw new IllegalArgumentException(
                    "License expiration date cannot be blank"
            );
        }
    }
}