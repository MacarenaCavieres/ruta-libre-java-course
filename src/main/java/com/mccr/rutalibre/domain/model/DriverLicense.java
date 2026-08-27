package com.mccr.rutalibre.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@Schema(description = "Detalles de la licencia de conducir del cliente")
public class DriverLicense {

    @Schema(description = "Clase de la licencia de conducir", example = "B")
    private String type = "B";

    @Schema(description = "Fecha de vencimiento de la licencia", example = "2030-01-01")
    private LocalDate expirationDate = LocalDate.of(2030, 1, 1);
}