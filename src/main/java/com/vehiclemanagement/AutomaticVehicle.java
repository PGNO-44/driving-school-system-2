package com.vehiclemanagement;

public class AutomaticVehicle extends Vehicle {
    public AutomaticVehicle(String make, String model, int year, String color, String registrationNumber, String maintenanceInfo) {
        super(make, model, year, color, registrationNumber, maintenanceInfo);
    }

    @Override
    public String getTransmissionType() {
        return "Automatic";
    }
}