package com.mccr.rutalibre.domain.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "DTO para actualizar las fechas de una reserva existente")
public record UpdateBookingRequest(
        @NotNull(message = "La fecha de inicio de la reserva no puede ser nula")
        @FutureOrPresent(message = "La fecha de inicio debe ser hoy o una fecha futura")
        @Schema(description = "Nueva fecha de inicio del arriendo", example = "2026-09-02")
        LocalDate startDate,

        @NotNull(message = "La fecha de fin de la reserva no puede ser nula")
        @Future(message = "La fecha de fin debe ser una fecha futura")
        @Schema(description = "Nueva fecha de término del arriendo", example = "2026-09-06")
        LocalDate endDate
) {
}
