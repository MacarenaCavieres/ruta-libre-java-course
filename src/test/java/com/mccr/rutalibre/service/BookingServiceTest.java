package com.mccr.rutalibre.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingServiceTest {

    @Test
    @DisplayName("Should verify the data of the customer renting the car")
    void shouldVerifyDataOfTheClientBookingTheCar(){
        //Arrange
        Client client = new Client();
        DiverLicense license = new DriverLicense();

        license.setType("B");
        license.setExpirationDate("01-01-2029");
        client.setId(1L);
        client.setName("Claudia");
        client.setLastname("Cifuentes");
        client.setRut("11111111-1");
        client.setEmail("claudia@mail.com");
        client.setPhone("+5691111111");
        client.setDriverLicense(license);

        //Act
        String name = client.getName();
        String lastname = client.lastname();
        String rut = client.rut();


        //Assert
        assertEquals("Claudia",name);
        assertEquals("Cifuentes",lastname);
        assertEquals("11111111-1",rut);


    }

    @Test
    @DisplayName("Should throw exception when the client doesn't exist")
    void shouldThrowClientNotFoundWhenClientDoesNotExist(){
        //Arrange
        Client client = null;

        //Act

        assertThrows(ClientNotFound.class, ()->)



    }
}
