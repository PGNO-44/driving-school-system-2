package com.vehiclemanagement;

public class VehicleDTO {
    private String make;
    private String model;
    private int year;
    private String color;
    private String registrationNumber;
    private String maintenanceInfo;
    private String transmissionType;

    // Default constructor (required for Jackson deserialization)
    public VehicleDTO() {}

    // Getters and Setters
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
    public String getTransmissionType() { return transmissionType; }
    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }
}