package com.mccr.rutalibre.domain.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "DTO para la creación de una nueva reserva")
public record CreateBookingRequest(
        @NotNull(message = "El id del cliente es obligatorio")
        @Schema(description = "ID del cliente que realiza la reserva", example = "1")
        Long clientId,

        @NotNull(message = "El id del vehiculo es obligatorio")
        @Schema(description = "ID del vehículo a reservar", example = "1")
        Long vehicleId,

        @NotNull(message = "La fecha de inicio de la reserva no puede ser nula")
        @FutureOrPresent(message = "La fecha de inicio debe ser hoy o una fecha futura")
        @Schema(description = "Fecha de inicio del arriendo", example = "2026-09-01")
        LocalDate startDate,

        @NotNull(message = "La fecha de fin de la reserva no puede ser nula")
        @Future(message = "La fecha de fin debe ser una fecha futura")
        @Schema(description = "Fecha de término del arriendo", example = "2026-09-05")
        LocalDate endDate
) {
}
