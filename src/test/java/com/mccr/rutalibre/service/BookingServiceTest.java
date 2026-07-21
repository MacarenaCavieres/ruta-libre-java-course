package com.mccr.rutalibre.service;

import com.mccr.rutalibre.exception.ClientNotFoundException;
import com.mccr.rutalibre.exception.InvalidBookingDateException;
import com.mccr.rutalibre.exception.VehicleNotAvailableException;
import com.mccr.rutalibre.model.Booking;
import com.mccr.rutalibre.model.Client;
import com.mccr.rutalibre.model.DriverLicense;
import com.mccr.rutalibre.model.Vehicle;
import com.mccr.rutalibre.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        bookingService = new BookingService(bookingRepository);
    }

    @Test
    @DisplayName("Should throw exception when the client doesn't exist")
    void shouldThrowClientNotFoundWhenClientDoesNotExist() {
        //Arrange
        LocalDate startDate = LocalDate.of(2026, 7, 21);
        LocalDate endDate = LocalDate.of(2026, 7, 26);

        Client client = buildClient();
        client.setId(null);

        Booking booking = new Booking(1L, client, null, startDate, endDate);

        //Act
        ClientNotFoundException ex = assertThrows(ClientNotFoundException.class, () -> bookingService.createBooking(booking));

        // Assert
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when the vehicle is not available")
    void shouldThrowVehicleNotAvailableExceptionWhenVehicleIsNotAvailable() {
        //Arrange
        LocalDate startDate = LocalDate.of(2026, 7, 21);
        LocalDate endDate = LocalDate.of(2026, 7, 26);

        Vehicle vehicle = buildVehicle();
        vehicle.setAvailable(false);

        Booking booking = new Booking(1L, buildClient(), vehicle, startDate, endDate);


        //Act
        VehicleNotAvailableException ex = assertThrows(VehicleNotAvailableException.class, () -> bookingService.createBooking(booking));

        // Assert
        assertEquals("El vehiculo no esta disponible", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when the end date is before the start date")
    void shouldThrowInvalidBookingDateExceptionWhenEndDateIsBeforeStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2026, 7, 27);
        LocalDate endDate = LocalDate.of(2026, 7, 26);


        Booking booking = new Booking(1L, buildClient(), buildVehicle(), startDate, endDate);


        //Act
        InvalidBookingDateException ex = assertThrows(InvalidBookingDateException.class, () -> bookingService.createBooking(booking));

        // Assert
        assertEquals("La fecha de inicio no puede ser superior a la fecha de término de la reserva", ex.getMessage());
    }

    @Test
    @DisplayName("Should save the booking")
    void shouldSaveTheBooking() {
        //Arrange
        LocalDate startDate = LocalDate.of(2026, 7, 21);
        LocalDate endDate = LocalDate.of(2026, 7, 26);

        Booking booking = new Booking(1L, buildClient(), buildVehicle(), startDate, endDate);

        //Act
        Booking response = bookingService.createBooking(booking);

        //Assert
        assertNotNull(response);
        assertSame(booking, response);

        verify(bookingRepository, times(1)).save(booking);

    }

    private DriverLicense buildLicense() {
        return new DriverLicense("B", "01-01-2029");
    }

    private Client buildClient() {
        DriverLicense license = buildLicense();
        return new Client(1L, "Claudia", "Cifuentes", license);
    }

    private Vehicle buildVehicle() {
        return new Vehicle(1L, "AAAA00", "MG", "3", true);
    }

}
