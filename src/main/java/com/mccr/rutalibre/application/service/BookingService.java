package com.mccr.rutalibre.application.service;

import com.mccr.rutalibre.domain.exception.ClientNotFoundException;
import com.mccr.rutalibre.domain.exception.InvalidBookingDateException;
import com.mccr.rutalibre.domain.model.Booking;
import com.mccr.rutalibre.domain.repository.BookingRepository;

public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        if (booking.client().getId() == null) {
            throw new ClientNotFoundException("Usuario no encontrado");
        }

//        if (!booking.getVehicle().isAvailable()) {
//            throw new VehicleNotAvailableException("El vehiculo no esta disponible");
//        }

        if (booking.startDate().isAfter(booking.endDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        bookingRepository.save(booking);

        return booking;

    }
}
