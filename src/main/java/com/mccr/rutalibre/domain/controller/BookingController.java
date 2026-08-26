package com.mccr.rutalibre.domain.controller;

import com.mccr.rutalibre.application.service.BookingService;
import com.mccr.rutalibre.domain.dto.booking.CreateBookingRequest;
import com.mccr.rutalibre.domain.dto.booking.UpdateBookingRequest;
import com.mccr.rutalibre.domain.model.Booking;
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
@Tag(name = "Reservas", description = "Endpoints para la gestión de reservas de vehículos")
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Crear una nueva reserva", description = "Registra una nueva reserva en el sistema asociada a un cliente y un vehículo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes (ej. fechas no válidas o datos faltantes)"),
            @ApiResponse(responseCode = "404", description = "Cliente o vehículo no encontrado"),
            @ApiResponse(responseCode = "500", description = "La Reserva no se pudo crear")
    })
    @PostMapping("/booking")
    public ResponseEntity<Booking> createNewBooking(@Valid @RequestBody CreateBookingRequest booking) {
        Booking newBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
    }

    @Operation(summary = "Obtener todas las reservas", description = "Retorna el listado completo de reservas registradas en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reservas obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getBookings();
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Actualizar una reserva por ID", description = "Modifica los datos de una reserva existente según su ID identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada con el ID especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/booking/{id}")
    public ResponseEntity<Booking> updateBookingById(
            @Parameter(description = "ID de la reserva a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookingRequest booking) {
        Booking bookingUpdated = bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(bookingUpdated);
    }

    @Operation(summary = "Eliminar una reserva", description = "Cancela o remueve una reserva del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada con el ID especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Map<String, String>> removeBooking(
            @Parameter(description = "ID de la reserva a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        String message = bookingService.deleteBooking(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}