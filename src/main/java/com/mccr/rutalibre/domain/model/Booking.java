package com.mccr.rutalibre.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo cliente no puede estar vacío")
    @Column(nullable = false)
    private Client client;

    @NotBlank(message = "El campo vehículo no puede estar vacío")
    @Column(nullable = false)
    private Vehicle vehicle;

    @NotBlank(message = "La fecha de inicio de la reserva no puede ir vacía")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotBlank(message = "La fecha de fin de la reserva no puede ir vacía")
    @Column(nullable = false)
    private LocalDate endDate;

}