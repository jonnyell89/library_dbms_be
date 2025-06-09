package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // CREATE
    @PostMapping // ("/api/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // READ
    @GetMapping // ("/api/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}") // ("/api/reservations/{id}")
    public Reservation getReservationById(Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    // UPDATE

    // DELETE
    @DeleteMapping("/{id}") // ("/api/reservations/{id}")
    public String deleteReservationById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
        return String.format("Reservation deleted with ID: " + reservationId);
    }
}
