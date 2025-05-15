package com.vehiclemanagement;

public class ManualVehicle extends Vehicle {
    public ManualVehicle(String make, String model, int year, String color, String registrationNumber, String maintenanceInfo) {
        super(make, model, year, color, registrationNumber, maintenanceInfo);
    }

    @Override
    public String getTransmissionType() {
        return "Manual";
    }
}