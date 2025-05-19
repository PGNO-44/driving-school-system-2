package com.vehiclehandling.service;

import com.vehiclehandling.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// This class helps save vehicles to a file (data.txt) and load them back
// The "@Service" tag tells Spring this class does important work
@Service
public class FileService {
    private final Path filePath = Paths.get("data.txt");

    // This reads all vehicles from the file and turns them into a list
    public List<Vehicle> readVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        vehicles.add(Vehicle.fromString(line));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();// If something goes wrong (like the file can’t be read), show the error
        }
        return vehicles;
    }

    // This saves all vehicles to the file by turning them into text lines
    public void writeVehicles(List<Vehicle> vehicles) {
        try {
            List<String> lines = new ArrayList<>();
            for (Vehicle v : vehicles) {
                lines.add(v.toString());
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}