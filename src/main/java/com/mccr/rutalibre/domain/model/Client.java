package com.mccr.rutalibre.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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

    @NotBlank(message = "La licencia no puede estar vacía")
    @Column(nullable = false)
    private DriverLicense license;


}