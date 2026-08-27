package com.mccr.rutalibre.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Entidad que representa una reserva de vehículo en el sistema")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la reserva", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @Schema(description = "Cliente asociado a la reserva")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @Schema(description = "Vehículo reservado")
    private Vehicle vehicle;

    @NotNull(message = "La fecha de inicio de la reserva no puede ir vacía")
    @Column(nullable = false)
    @Schema(description = "Fecha de inicio del arriendo", example = "2026-09-01")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin de la reserva no puede ir vacía")
    @Column(nullable = false)
    @Schema(description = "Fecha de término del arriendo", example = "2026-09-05")
    private LocalDate endDate;

    public Booking(Client client, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.client = client;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}