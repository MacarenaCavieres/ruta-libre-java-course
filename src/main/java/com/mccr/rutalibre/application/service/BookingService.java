package com.mccr.rutalibre.application.service;

import com.mccr.rutalibre.domain.exception.BookingNotCreatedException;
import com.mccr.rutalibre.domain.exception.BookingNotFoundException;
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
        if (booking.getStartDate().isAfter(booking.getEndDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        Booking newBooking = bookingRepository.save(booking);

        if (newBooking.getId() == null) {
            throw new BookingNotCreatedException("La Reserva no se pudo crear");
        }
        return newBooking;

    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(Long id, Booking booking) {
        Booking oldBooking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Reserva no encontrada"));

        if (booking.getStartDate().isAfter(booking.getEndDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        oldBooking.setClient(booking.getClient());
        oldBooking.setVehicle(booking.getVehicle());
        oldBooking.setStartDate(booking.getStartDate());
        oldBooking.setEndDate(booking.getEndDate());

        bookingRepository.save(oldBooking);

        return oldBooking;
    }

    public String deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Reserva no encontrada");
        }

        bookingRepository.deleteById(id);

        return "Reserva eliminada correctamente";
    }
}
