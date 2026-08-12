package com.mccr.rutalibre.application.usecase;

import com.mccr.rutalibre.domain.model.Booking;
import com.mccr.rutalibre.domain.repository.BookingRepository;

public class CreateBookingUseCase {

    private final BookingRepository bookingRepository;

    public CreateBookingUseCase(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking execute(Booking booking) {

        if (bookingRepository.findById(booking.id()).isPresent()) {
            throw new IllegalStateException(
                    "Booking is already registered"
            );
        }

        booking.vehicle().reserve();

        bookingRepository.save(booking);

        return booking;
    }
}