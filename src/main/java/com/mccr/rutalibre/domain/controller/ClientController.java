package com.mccr.rutalibre.domain.controller;

import com.mccr.rutalibre.application.service.ClientService;
import com.mccr.rutalibre.domain.model.Client;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {
    final private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<Client> createNewClient(@Valid @RequestBody Client client) {
        Client newClient = clientService.createClient(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getClients();

        return ResponseEntity.ok(clients);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClientById(@PathVariable Long id, @Valid @RequestBody Client client) {
        Client clientUpdated = clientService.updateClient(id, client);

        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Map<String, String>> removeClient(@PathVariable Long id) {
        String message = clientService.deleteClient(id);

        return ResponseEntity.ok(Map.of("message", message));
    }
}
