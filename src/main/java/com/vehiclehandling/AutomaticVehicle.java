package com.vehiclehandling;

public class AutomaticVehicle extends Vehicle {
    public AutomaticVehicle(int id, String make, String model, String registrationNumber, String maintenanceSchedule,
                            String color, int year) {
        super(id, make, model, registrationNumber, maintenanceSchedule, color, year, "Automatic");
    }
}