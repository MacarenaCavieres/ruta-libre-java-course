package com.mccr.rutalibre.domain.model;

import com.mccr.rutalibre.domain.model.enums.VehicleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@Schema(description = "Entidad que representa un vehículo dentro de la flota")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del vehículo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "La patente no puede estar vacía")
    @Column(nullable = false, unique = true)
    @Schema(description = "Patente única del vehículo", example = "BBCL-12")
    private String plate;

    @NotBlank(message = "La marca del vehículo no puede estar vacía")
    @Column(nullable = false)
    @Schema(description = "Marca del fabricante", example = "Toyota")
    private String brand;

    @NotBlank(message = "El modelo del vehículo no puede estar vacío")
    @Column(nullable = false)
    @Schema(description = "Modelo específico", example = "RAV4")
    private String model;

    @NotBlank(message = "El año del vehículo no puede estar vacío")
    @Column(nullable = false)
    @Schema(description = "Año de fabricación", example = "2024")
    private String year;

    @NotNull(message = "El estado del vehículo no puede estar vacío")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Estado operativo actual", example = "AVAILABLE")
    private VehicleStatus status = VehicleStatus.AVAILABLE;
}