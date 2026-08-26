package com.mccr.rutalibre.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(nullable = false)
    private String lastname;

    @Embedded
    private DriverLicense license = new DriverLicense();
}