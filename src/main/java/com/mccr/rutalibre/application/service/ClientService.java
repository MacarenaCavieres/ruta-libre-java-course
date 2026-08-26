package com.mccr.rutalibre.application.service;

import com.mccr.rutalibre.domain.exception.ClientNotFoundException;
import com.mccr.rutalibre.domain.model.Client;
import com.mccr.rutalibre.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    final private ClientRepository clientRepository;

    public Client createClient(Client client) {
        Client newClient = clientRepository.save(client);

        if (newClient.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error: No se pudo crear el cliente");
        }

        return newClient;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(Long id, Client client) {
        Client oldClient = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado"));

        oldClient.setName(client.getName());
        oldClient.setLastname(client.getLastname());
        oldClient.setLicense(client.getLicense());

        clientRepository.save(oldClient);

        return oldClient;
    }

    public String deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Cliente no encontrado");
        }

        clientRepository.deleteById(id);

        return "Cliente eliminado correctamente";
    }

}
