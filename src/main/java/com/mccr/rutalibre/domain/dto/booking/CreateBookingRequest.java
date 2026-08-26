package com.mccr.rutalibre.domain.dto.booking;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateBookingRequest(@NotNull(message = "El id del cliente es obligatorio") Long clientId,
                                   @NotNull(message = "El id del vehiculo es obligatorio") Long vehicleId,
                                   @NotBlank(message = "La fecha de inicio de la reserva no puede ir vacía") LocalDate startDate,
                                   @NotBlank(message = "La fecha de fin de la reserva no puede ir vacía") LocalDate endDate) {
}
