package com.vehiclehandling.controller;

import com.vehiclehandling.entity.AutomaticVehicle;
import com.vehiclehandling.entity.ManualVehicle;
import com.vehiclehandling.entity.Vehicle;
import com.vehiclehandling.service.VehicleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class listens for messages (requests) from the website and decides what to do
// The "@Controller" tag tells Spring this class handles web requests
@Controller
public class VehicleController {
    @Autowired
    // We need the VehicleManager to do the actual work (like adding or deleting vehicles)
    private VehicleManager vehicleManager;

    @GetMapping("/")
    public String getVehicleList() {
        return "redirect:/vehicle_list.html";
    }

    // When someone asks for all vehicles ("/vehicles"), send them a list as text
    @GetMapping("/vehicles")
    @ResponseBody
    public String getVehicles() {
        List<Vehicle> vehicles = vehicleManager.getVehicles();
        StringBuilder result = new StringBuilder();
        for (Vehicle v : vehicles) {
            result.append(v.getId()).append(",")
                    .append(v.getMake()).append(",")
                    .append(v.getModel()).append(",")
                    .append(v.getYear()).append(",")
                    .append(v.getColor()).append(",")
                    .append(v.getRegistrationNumber()).append(",")
                    .append(v.getMaintenanceSchedule()).append(",")
                    .append(v.getTransmissionType()).append("\n");
        }
        return result.length() > 0 ? result.toString() : "No vehicles found.";
    }

    // When someone asks for one vehicle ("/vehicle/1"), send its details as text
    @GetMapping("/vehicle/{id}")
    @ResponseBody
    public String getVehicle(@PathVariable int id) {
        Vehicle v = vehicleManager.getVehicleById(id);
        if (v == null) {
            return "Vehicle not found.";
        }
        return v.getId() + "," + v.getMake() + "," + v.getModel() + "," + v.getYear() + "," +
                v.getColor() + "," + v.getRegistrationNumber() + "," + v.getMaintenanceSchedule() + "," +
                v.getTransmissionType();
    }

    // When someone wants to add a new vehicle (POST to "/vehicle"), add it
    @PostMapping("/vehicle")
    @ResponseBody
    public String addVehicle(@RequestParam String make, @RequestParam String model,
                             @RequestParam int year, @RequestParam String color,
                             @RequestParam String registrationNumber, @RequestParam String maintenanceSchedule,
                             @RequestParam String transmissionType) {
        int id = vehicleManager.getNextId();
        Vehicle vehicle = transmissionType.equals("Manual") ?
                new ManualVehicle(id, make, model, registrationNumber, maintenanceSchedule, color, year) :
                new AutomaticVehicle(id, make, model, registrationNumber, maintenanceSchedule, color, year);
        vehicleManager.addVehicle(vehicle);
        return "Vehicle added successfully.";
    }

    @PostMapping("/vehicle/{id}")
    @ResponseBody
    public String updateVehicle(@PathVariable int id, @RequestParam String make, @RequestParam String model,
                                @RequestParam int year, @RequestParam String color,
                                @RequestParam String registrationNumber, @RequestParam String maintenanceSchedule,
                                @RequestParam String transmissionType) {
        vehicleManager.updateVehicle(id, make, model, registrationNumber, maintenanceSchedule, color, year, transmissionType);
        return "Vehicle updated successfully.";
    }

    @DeleteMapping("/vehicle/{id}")
    @ResponseBody
    public String deleteVehicle(@PathVariable int id) {
        vehicleManager.deleteVehicle(id);
        return "Vehicle deleted successfully.";
    }
}