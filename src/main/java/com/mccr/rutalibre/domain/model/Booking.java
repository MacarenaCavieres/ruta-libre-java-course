package com.mccr.rutalibre.domain.model;

import java.time.LocalDate;

public record Booking(Long id, Client client, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {

    public Booking {
        if (id == null) {
            throw new IllegalArgumentException("Booking id cannot be null");
        }

        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Booking dates cannot be null");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start date cannot be after end date"
            );
        }

    }
}