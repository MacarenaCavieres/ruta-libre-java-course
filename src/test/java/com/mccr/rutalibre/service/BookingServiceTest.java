package com.mccr.rutalibre.service;

import com.mccr.rutalibre.exception.ClientNotFoundException;
import com.mccr.rutalibre.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Test
    @DisplayName("Should verify the data of the customer renting the car")
    void shouldVerifyDataOfTheClientBookingTheCar() {
        //Arrange
        Client client = new Client(1L, "Claudia", "Cifuentes", "11111111-1", "claudia@mail.com");

        //Act & Assert
        assertEquals("Claudia", client.getName());
        assertEquals("Cifuentes", client.getLastname());
        assertEquals("11111111-1", client.getRut());
    }

    @Test
    @DisplayName("Should throw exception when the client doesn't exist")
    void shouldThrowClientNotFoundWhenClientDoesNotExist() {
        //Arrange
        BookingService bookingService = new BookingService();
        Client client = null;

        //Act & Assert
        assertThrows(ClientNotFoundException.class, () ->bookingService.createBooking(null));
    }
}
