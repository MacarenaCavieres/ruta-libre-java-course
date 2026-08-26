package com.mccr.rutalibre.application.service;

import com.mccr.rutalibre.domain.exception.VehicleNotCreatedException;
import com.mccr.rutalibre.domain.exception.VehicleNotFoundException;
import com.mccr.rutalibre.domain.model.Vehicle;
import com.mccr.rutalibre.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    final private VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        if (newVehicle.getId() == null) {
            throw new VehicleNotCreatedException("El vehículo no se pudo crear");
        }

        return newVehicle;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle oldVehicle = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException("Vehículo no encontrado"));

        oldVehicle.setPlate(vehicle.getPlate());
        oldVehicle.setBrand(vehicle.getBrand());
        oldVehicle.setModel(vehicle.getModel());
        oldVehicle.setYear(vehicle.getYear());
        oldVehicle.setStatus(vehicle.getStatus());

        vehicleRepository.save(oldVehicle);

        return oldVehicle;
    }

    public String deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException("Vehículo no encontrado");
        }

        vehicleRepository.deleteById(id);

        return "Vehículo eliminado correctamente";
    }
}
