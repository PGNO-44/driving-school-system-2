package com.vehiclemanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final String FILE_PATH = "vehicles.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Vehicle> vehicles;

    public VehicleService() {
        vehicles = loadVehicles();
    }

    private List<Vehicle> loadVehicles() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Vehicle.class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveVehicles() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), vehicles);
        } catch (IOException e) {
            throw new RuntimeException("Error saving vehicles", e);
        }
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveVehicles();
        return vehicle;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Optional<Vehicle> getVehicleById(String id) {
        return vehicles.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public Optional<Vehicle> updateVehicle(String id, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicle = getVehicleById(id);
        existingVehicle.ifPresent(v -> {
            v.setMake(updatedVehicle.getMake());
            v.setModel(updatedVehicle.getModel());
            v.setYear(updatedVehicle.getYear());
            v.setColor(updatedVehicle.getColor());
            v.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
            v.setMaintenanceInfo(updatedVehicle.getMaintenanceInfo());
            saveVehicles();
        });
        return existingVehicle;
    }

    public boolean deleteVehicle(String id) {
        boolean removed = vehicles.removeIf(v -> v.getId().equals(id));
        if (removed) {
            saveVehicles();
        }
        return removed;
    }
}