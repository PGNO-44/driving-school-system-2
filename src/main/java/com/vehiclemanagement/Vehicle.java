package com.vehiclemanagement;

import java.util.UUID;

public abstract class Vehicle {
    private String id;
    private String make;
    private String model;
    private int year;
    private String color;
    private String registrationNumber;
    private String maintenanceInfo;

    public Vehicle() {
        this.id = UUID.randomUUID().toString();
    }

    public Vehicle(String make, String model, int year, String color, String registrationNumber, String maintenanceInfo) {
        this.id = UUID.randomUUID().toString();
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.maintenanceInfo = maintenanceInfo;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public String getMaintenanceInfo() { return maintenanceInfo; }
    public void setMaintenanceInfo(String maintenanceInfo) { this.maintenanceInfo = maintenanceInfo; }

    public abstract String getTransmissionType();
}