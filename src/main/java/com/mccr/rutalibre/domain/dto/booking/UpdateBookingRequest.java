package com.mccr.rutalibre.domain.dto.booking;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateBookingRequest(
        @NotBlank(message = "La fecha de inicio de la reserva no puede ir vacía") LocalDate startDate,
        @NotBlank(message = "La fecha de fin de la reserva no puede ir vacía") LocalDate endDate) {
}
