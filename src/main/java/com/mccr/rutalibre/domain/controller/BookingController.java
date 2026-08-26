package com.mccr.rutalibre.domain.controller;

import com.mccr.rutalibre.application.service.BookingService;
import com.mccr.rutalibre.domain.model.Booking;
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
public class BookingController {

    final private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<Booking> createNewBooking(@Valid @RequestBody Booking booking) {
        Booking newBooking = bookingService.createBooking(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
    }

    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getBookings();

        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<Booking> updateBookingById(@PathVariable Long id, @Valid @RequestBody Booking booking) {
        Booking bookingUpdated = bookingService.updateBooking(id, booking);

        return ResponseEntity.ok(bookingUpdated);
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Map<String, String>> removeBooking(@PathVariable Long id) {
        String message = bookingService.deleteBooking(id);

        return ResponseEntity.ok(Map.of("message", message));
    }
}
