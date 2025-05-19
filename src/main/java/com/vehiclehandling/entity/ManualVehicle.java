package com.vehiclehandling.entity;

public class ManualVehicle extends Vehicle {
    public ManualVehicle(int id, String make, String model, String registrationNumber, String maintenanceSchedule,
                         String color, int year) {
        super(id, make, model, registrationNumber, maintenanceSchedule, color, year, "Manual");
    }
}