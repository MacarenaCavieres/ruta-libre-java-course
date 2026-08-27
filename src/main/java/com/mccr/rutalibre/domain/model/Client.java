package com.mccr.rutalibre.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Schema(description = "Entidad que representa a un cliente registrado")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del cliente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    @Schema(description = "Nombre(s) del cliente", example = "Juan")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(nullable = false)
    @Schema(description = "Apellido(s) del cliente", example = "Pérez")
    private String lastname;

    @Embedded
    @Schema(description = "Licencia de conducir asociada al cliente")
    private DriverLicense license = new DriverLicense();
}