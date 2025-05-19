package com.vehiclehandling.service;

import com.vehiclehandling.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// This class is like the "manager" of all vehicles—it keeps track of them and handles adding, updating, etc.
// The "@Service" tag tells Spring this class does important work behind the scenes
@Service
public class VehicleManager {
    private List<Vehicle> vehicles = new ArrayList<>();
    // This helps us save the vehicles to a file
    private FileService fileService;

    // When we create a VehicleManager, we need a FileService to save/load vehicles
    // On start, we load all vehicles from the file into our list
    public VehicleManager(FileService fileService) {
        this.fileService = fileService;
        this.vehicles = fileService.readVehicles();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        fileService.writeVehicles(vehicles);
    }

    // This gives a copy of all vehicles in our list (so others can’t accidentally change it)
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