package com.mccr.rutalibre.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Setter
@Getter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @NotBlank(message = "La fecha de inicio de la reserva no puede ir vacía")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotBlank(message = "La fecha de fin de la reserva no puede ir vacía")
    @Column(nullable = false)
    private LocalDate endDate;

}