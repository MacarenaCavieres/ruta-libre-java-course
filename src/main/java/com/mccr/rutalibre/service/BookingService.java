package com.mccr.rutalibre.service;

import com.mccr.rutalibre.exception.ClientNotFoundException;
import com.mccr.rutalibre.exception.InvalidBookingDateException;
import com.mccr.rutalibre.exception.VehicleNotAvailableException;
import com.mccr.rutalibre.model.Booking;
import com.mccr.rutalibre.repository.BookingRepository;

public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        if (booking.getClient().getId() == null) {
            throw new ClientNotFoundException("Usuario no encontrado");
        }

        if (!booking.getVehicle().isAvailable()) {
            throw new VehicleNotAvailableException("El vehiculo no esta disponible");
        }

        if (booking.getStartDate().isAfter(booking.getEndDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        bookingRepository.save(booking);

        return booking;

    }
}
