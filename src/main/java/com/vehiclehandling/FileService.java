package com.vehiclehandling;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final Path filePath = Paths.get("data.txt");

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
            e.printStackTrace();
        }
        return vehicles;
    }

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