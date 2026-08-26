package com.mccr.rutalibre.domain.controller;

import com.mccr.rutalibre.application.service.VehicleService;
import com.mccr.rutalibre.domain.model.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Vehículos", description = "Endpoints para la gestión e inventario de la flota de vehículos")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "Registrar un nuevo vehículo", description = "Agrega un nuevo vehículo al inventario de la flota.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehículo registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. patente duplicada)"),
            @ApiResponse(responseCode = "500", description = "Vehículo no creado; Error interno del servidor")
    })
    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> createNewVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    @Operation(summary = "Obtener todos los vehículos", description = "Retorna el listado completo de vehículos en la flota.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de vehículos obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/vehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @Operation(summary = "Actualizar un vehículo por ID", description = "Modifica los datos o estado de un vehículo existente según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehículo actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
            @ApiResponse(responseCode = "404", description = "Vehículo no encontrado con el ID especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicleById(
            @Parameter(description = "ID del vehículo a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Vehicle vehicle) {
        Vehicle vehicleUpdated = vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(vehicleUpdated);
    }

    @Operation(summary = "Eliminar un vehículo", description = "Elimina un vehículo de la flota mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehículo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Vehículo no encontrado con el ID especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<Map<String, String>> removeVehicle(
            @Parameter(description = "ID del vehículo a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        String message = vehicleService.deleteVehicle(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}