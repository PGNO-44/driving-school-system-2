package com.vehiclehandling;

public abstract class Vehicle {
    private int id;
    private String make;
    private String model;
    private String registrationNumber;
    private String maintenanceSchedule;
    private String color;
    private int year;
    private String transmissionType;

    public Vehicle(int id, String make, String model, String registrationNumber, String maintenanceSchedule,
                   String color, int year, String transmissionType) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.maintenanceSchedule = maintenanceSchedule;
        this.color = color;
        this.year = year;
        this.transmissionType = transmissionType;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void updateVehicle(String make, String model, String registrationNumber, String maintenanceSchedule,
                              String color, int year, String transmissionType) {
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.maintenanceSchedule = maintenanceSchedule;
        this.color = color;
        this.year = year;
        this.transmissionType = transmissionType;
    }

    @Override
    public String toString() {
        return id + "," + make + "," + model + "," + registrationNumber + "," +
                maintenanceSchedule + "," + color + "," + year + "," + transmissionType;
    }

    public static Vehicle fromString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String make = parts[1].trim();
        String model = parts[2].trim();
        String registrationNumber = parts[3].trim();
        String maintenanceSchedule = parts[4].trim();
        String color = parts[5].trim();
        int year = Integer.parseInt(parts[6].trim());
        String transmissionType = parts[7].trim();
        if (transmissionType.equalsIgnoreCase("Manual")) {
            return new ManualVehicle(id, make, model, registrationNumber, maintenanceSchedule, color, year);
        } else {
            return new AutomaticVehicle(id, make, model, registrationNumber, maintenanceSchedule, color, year);
        }
    }
}