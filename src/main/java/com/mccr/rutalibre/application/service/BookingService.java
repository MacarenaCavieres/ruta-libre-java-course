package com.mccr.rutalibre.application.service;

import com.mccr.rutalibre.domain.exception.ClientNotFoundException;
import com.mccr.rutalibre.domain.exception.InvalidBookingDateException;
import com.mccr.rutalibre.domain.model.Booking;
import com.mccr.rutalibre.domain.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        if (booking.startDate().isAfter(booking.endDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        bookingRepository.save(booking);

        return booking;

    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(Booking booking) {
        Booking oldBooking = bookingRepository.findById(booking.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));


        return booking;
    }
}
