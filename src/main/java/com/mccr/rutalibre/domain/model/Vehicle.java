package com.mccr.rutalibre.domain.model;

import com.mccr.rutalibre.domain.model.enums.VehicleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La patente no puede estar vacía")
    @Column(nullable = false, unique = true)
    private String plate;

    @NotBlank(message = "La marca del vehículo no puede estar vacía")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "El modelo del vehículo no puede estar vacío")
    @Column(nullable = false)
    private String model;

    @NotBlank(message = "El año del vehículo no puede estar vacío")
    @Column(nullable = false)
    private String year;

    @NotNull(message = "El estado del vehículo no puede estar vacío")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleStatus status = VehicleStatus.AVAILABLE;

}