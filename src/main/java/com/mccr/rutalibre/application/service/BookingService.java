package com.mccr.rutalibre.application.service;

import com.mccr.rutalibre.domain.dto.booking.CreateBookingRequest;
import com.mccr.rutalibre.domain.dto.booking.UpdateBookingRequest;
import com.mccr.rutalibre.domain.exception.*;
import com.mccr.rutalibre.domain.model.Booking;
import com.mccr.rutalibre.domain.model.Client;
import com.mccr.rutalibre.domain.model.Vehicle;
import com.mccr.rutalibre.domain.repository.BookingRepository;
import com.mccr.rutalibre.domain.repository.ClientRepository;
import com.mccr.rutalibre.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    public Booking createBooking(CreateBookingRequest booking) {
        if (booking.startDate().isAfter(booking.endDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        Client client = clientRepository.findById(booking.clientId()).orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado"));

        Vehicle vehicle = vehicleRepository.findById(booking.vehicleId()).orElseThrow(() -> new VehicleNotFoundException("Vehículo no encontrado"));

        Booking bookingToCreate = new Booking(client, vehicle, booking.startDate(), booking.endDate());

        Booking newBooking = bookingRepository.save(bookingToCreate);

        if (newBooking.getId() == null) {
            throw new BookingNotCreatedException("La Reserva no se pudo crear");
        }
        return newBooking;

    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(Long id, UpdateBookingRequest booking) {
        Booking oldBooking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Reserva no encontrada"));

        if (booking.startDate().isAfter(booking.endDate())) {
            throw new InvalidBookingDateException("La fecha de inicio no puede ser superior a la fecha de término de la reserva");
        }

        oldBooking.setClient(oldBooking.getClient());
        oldBooking.setVehicle(oldBooking.getVehicle());
        oldBooking.setStartDate(booking.startDate());
        oldBooking.setEndDate(booking.endDate());

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
