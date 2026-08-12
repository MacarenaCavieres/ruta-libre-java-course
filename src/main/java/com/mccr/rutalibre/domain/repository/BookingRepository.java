package com.mccr.rutalibre.domain.repository;

import com.mccr.rutalibre.domain.model.Booking;

import java.util.Optional;

public interface BookingRepository {

    void save(Booking booking);

    Optional<Booking> findById(Long id);
}