package com.mccr.rutalibre.usecase;

import com.mccr.rutalibre.application.usecase.CreateBookingUseCase;
import com.mccr.rutalibre.domain.model.Booking;
import com.mccr.rutalibre.domain.model.Client;
import com.mccr.rutalibre.domain.model.DriverLicense;
import com.mccr.rutalibre.domain.model.Vehicle;
import com.mccr.rutalibre.domain.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateBookingUseCaseTest {

    private BookingRepository bookingRepository;
    private CreateBookingUseCase createBookingUseCase;

    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        createBookingUseCase = new CreateBookingUseCase(bookingRepository);
    }

    @Test
    void shouldCreateBooking() {

        Booking booking = buildBooking();

        Booking response = createBookingUseCase.execute(booking);

        assertNotNull(response);
        assertSame(booking, response);

        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void shouldNotCreateBookingWhenBookingAlreadyExists() {

        Booking booking = buildBooking();

        when(bookingRepository.findById(booking.id()))
                .thenReturn(java.util.Optional.of(booking));

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> createBookingUseCase.execute(booking)
        );

        assertEquals(
                "Booking is already registered",
                exception.getMessage()
        );

        verify(bookingRepository, never()).save(booking);
    }

    private Booking buildBooking() {

        DriverLicense license =
                new DriverLicense("B", "01-01-2029");

        Client client =
                new Client(1L, "Claudia", "Cifuentes", license);

        Vehicle vehicle =
                new Vehicle(
                        1L,
                        "AAAA00",
                        "MG",
                        "3",
                        "2025",
                        "SEDAN",
                        "AVAILABLE"
                );

        return new Booking(
                1L,
                client,
                vehicle,
                LocalDate.of(2026, 7, 21),
                LocalDate.of(2026, 7, 26)
        );
    }
}