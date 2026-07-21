package com.mccr.rutalibre.model;

public class DriverLicense {
    private String type;
    private String expirationDate;

    public DriverLicense(String type, String expirationDate) {
        this.type = type;
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
