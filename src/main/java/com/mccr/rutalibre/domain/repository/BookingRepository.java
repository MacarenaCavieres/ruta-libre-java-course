package com.mccr.rutalibre.domain.repository;

import com.mccr.rutalibre.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository<Booking, Long> {

}