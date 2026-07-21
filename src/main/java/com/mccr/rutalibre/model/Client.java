package com.mccr.rutalibre.model;

import java.time.Instant;

public class Client {
    private Long id;
    private String name;
    private String lastname;
    private DriverLicense license;

    public Client(Long id, String name, String lastname, DriverLicense license) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.license = license;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public DriverLicense getLicense() {
        return license;
    }

    public void setLicense(DriverLicense license) {
        this.license = license;
    }

}
