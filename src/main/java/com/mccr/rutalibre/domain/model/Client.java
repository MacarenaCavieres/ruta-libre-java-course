package com.mccr.rutalibre.domain.model;

public class Client {

    private final Long id;
    private String name;
    private String lastname;
    private final DriverLicense license;

    public Client(
            Long id,
            String name,
            String lastname,
            DriverLicense license
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Client name cannot be blank");
        }

        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Client lastname cannot be blank");
        }

        if (license == null) {
            throw new IllegalArgumentException("Driver license cannot be null");
        }

        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.license = license;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public DriverLicense getLicense() {
        return license;
    }
}