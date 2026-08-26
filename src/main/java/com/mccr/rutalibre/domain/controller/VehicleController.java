package com.mccr.rutalibre.domain.controller;

import com.mccr.rutalibre.application.service.VehicleService;
import com.mccr.rutalibre.domain.model.Vehicle;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VehicleController {
    final private VehicleService vehicleService;

    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> createNewVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleService.createVehicle(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    @GetMapping("/vehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehicles();

        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle) {
        Vehicle vehicleUpdated = vehicleService.updateVehicle(id, vehicle);

        return ResponseEntity.ok(vehicleUpdated);
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<Map<String, String>> removeVehicle(@PathVariable Long id) {
        String message = vehicleService.deleteVehicle(id);

        return ResponseEntity.ok(Map.of("message", message));
    }
}
