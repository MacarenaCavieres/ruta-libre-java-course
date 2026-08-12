package com.mccr.rutalibre.domain.model;

public class Vehicle {

    private final Long id;
    private final String plate;
    private final String brand;
    private final String model;
    private final String year;
    private final String category;
    private String status;

    public Vehicle(
            Long id,
            String plate,
            String brand,
            String model,
            String year,
            String category,
            String status
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Vehicle id cannot be null");
        }

        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Vehicle plate cannot be blank");
        }

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Vehicle brand cannot be blank");
        }

        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Vehicle model cannot be blank");
        }

        if (year == null || year.isBlank()) {
            throw new IllegalArgumentException("Vehicle year cannot be blank");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Vehicle category cannot be blank");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Vehicle status cannot be blank");
        }

        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void reserve() {
        if (!status.equals("AVAILABLE")) {
            throw new IllegalStateException(
                    "Vehicle is not available"
            );
        }

        status = "RENTED";
    }
}