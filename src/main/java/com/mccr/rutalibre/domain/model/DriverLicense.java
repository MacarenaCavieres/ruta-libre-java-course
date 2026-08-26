package com.mccr.rutalibre.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class DriverLicense {

    private String type = "B";
    private LocalDate expirationDate = LocalDate.of(2030, 1, 1);
}