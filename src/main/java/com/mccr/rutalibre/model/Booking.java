package com.mccr.rutalibre.model;

import java.time.LocalDate;

public class Booking {
    private Long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(Long id, Client client, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
