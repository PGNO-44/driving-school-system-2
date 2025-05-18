package com.vehiclehandling;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleManager {
    private List<Vehicle> vehicles = new ArrayList<>();
    private FileService fileService;

    public VehicleManager(FileService fileService) {
        this.fileService = fileService;
        this.vehicles = fileService.readVehicles();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        fileService.writeVehicles(vehicles);
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public Vehicle getVehicleById(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public void updateVehicle(int id, String make, String model, String registrationNumber, String maintenanceSchedule,
                              String color, int year, String transmissionType) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.updateVehicle(make, model, registrationNumber, maintenanceSchedule, color, year, transmissionType);
            fileService.writeVehicles(vehicles);
        }
    }

    public void deleteVehicle(int id) {
        vehicles.removeIf(v -> v.getId() == id);
        fileService.writeVehicles(vehicles);
    }

    public int getNextId() {
        int maxId = 0;
        for (Vehicle v : vehicles) {
            if (v.getId() > maxId) {
                maxId = v.getId();
            }
        }
        return maxId + 1;
    }
}