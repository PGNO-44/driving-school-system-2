package com.vehiclemanagement;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:8080")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle newVehicle;
        if ("Automatic".equalsIgnoreCase(vehicleDTO.getTransmissionType())) {
            newVehicle = new AutomaticVehicle(
                vehicleDTO.getMake(),
                vehicleDTO.getModel(),
                vehicleDTO.getYear(),
                vehicleDTO.getColor(),
                vehicleDTO.getRegistrationNumber(),
                vehicleDTO.getMaintenanceInfo()
            );
        } else {
            newVehicle = new ManualVehicle(
                vehicleDTO.getMake(),
                vehicleDTO.getModel(),
                vehicleDTO.getYear(),
                vehicleDTO.getColor(),
                vehicleDTO.getRegistrationNumber(),
                vehicleDTO.getMaintenanceInfo()
            );
        }
        return vehicleService.addVehicle(newVehicle);
    }

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id);
    }

    @PutMapping("/{id}")
    public Optional<Vehicle> updateVehicle(@PathVariable String id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public boolean deleteVehicle(@PathVariable String id) {
        return vehicleService.deleteVehicle(id);
    }
}