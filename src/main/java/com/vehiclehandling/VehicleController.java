package com.vehiclehandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleController {
    @Autowired
    private VehicleManager vehicleManager;

    @GetMapping("/")
    public String getVehicleList() {
        return "redirect:/vehicle_list.html";
    }

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