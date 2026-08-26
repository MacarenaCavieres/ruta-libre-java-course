package com.mccr.rutalibre.domain.controller;

import com.mccr.rutalibre.application.service.ClientService;
import com.mccr.rutalibre.domain.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Clientes", description = "Endpoints para la gestión e información de clientes")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Registrar un nuevo cliente", description = "Crea un nuevo registro de cliente en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "No se pudo crear el cliente; Error de servidor")
    })
    @PostMapping("/client")
    public ResponseEntity<Client> createNewClient(@Valid @RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @Operation(summary = "Obtener todos los clientes", description = "Retorna el listado completo de clientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de clientes obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/client")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Actualizar un cliente por ID", description = "Modifica la información de un cliente existente según su ID identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado con el ID especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClientById(
            @Parameter(description = "ID del cliente a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Client client) {
        Client clientUpdated = clientService.updateClient(id, client);
        return ResponseEntity.ok(clientUpdated);
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina el registro de un cliente del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado con el ID especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Map<String, String>> removeClient(
            @Parameter(description = "ID del cliente a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        String message = clientService.deleteClient(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}