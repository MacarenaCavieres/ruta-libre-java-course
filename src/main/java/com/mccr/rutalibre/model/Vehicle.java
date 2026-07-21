package com.mccr.rutalibre.model;

public class Vehicle {
    private Long id;
    private String plate;
    private String brand;
    private String model;
    private boolean available;

    public Vehicle(Long id, String plate, String brand, String model, boolean available) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
