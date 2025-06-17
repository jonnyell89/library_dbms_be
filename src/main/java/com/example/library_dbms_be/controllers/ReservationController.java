package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.dtos.ReservationRequestDTO;
import com.example.library_dbms_be.dtos.ReservationResponseDTO;
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
    public ReservationResponseDTO createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        return reservationService.createReservation(reservationRequestDTO);
    }

    // READ
    @GetMapping // ("/api/reservations")
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{reservationId}") // ("/api/reservations/{reservationId}")
    public ReservationResponseDTO getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    // UPDATE
    @PutMapping("/{reservationId}") // ("/api/reservations/{reservationId}")
    public ReservationResponseDTO updateReservationById(@PathVariable Long reservationId, @RequestBody ReservationRequestDTO reservationRequestDTO) {
        return reservationService.updateReservationById(reservationId, reservationRequestDTO);
    }

    // DELETE
    @DeleteMapping("/{reservationId}") // ("/api/reservations/{reservationId}")
    public String deleteReservationById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
        return String.format("Reservation deleted with ID: " + reservationId);
    }
}
