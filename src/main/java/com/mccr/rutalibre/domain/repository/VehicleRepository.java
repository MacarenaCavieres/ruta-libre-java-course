package com.mccr.rutalibre.domain.repository;

import com.mccr.rutalibre.domain.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
