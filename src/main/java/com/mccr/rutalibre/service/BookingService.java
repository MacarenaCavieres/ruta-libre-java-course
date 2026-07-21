package com.mccr.rutalibre.service;

import com.mccr.rutalibre.exception.ClientNotFoundException;
import com.mccr.rutalibre.model.Client;

public class BookingService {

    public String createBooking(Client client){
        if(client == null){
            throw new ClientNotFoundException("Usuario no encontrado");
        }

        return "Usuario creado";
    }
}
