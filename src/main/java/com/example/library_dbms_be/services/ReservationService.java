package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Member;
import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.repositories.MemberRepository;
import com.example.library_dbms_be.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    public final ReservationRepository reservationRepository;
    public final MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
    }

    // CREATE
    public Reservation createReservation(Reservation reservation) {

        // Handles Reservation object's associated Member object.
        Member incomingMember = reservation.getMember();

        if (incomingMember != null && incomingMember.getName() != null && incomingMember.getEmail() != null) {
            // Checks for persisted Member.
            Optional<Member> existingMember = memberRepository.findByNameAndEmail(
                    incomingMember.getName(),
                    incomingMember.getEmail()
            );

            if (existingMember.isPresent()) {
                reservation.setMember(existingMember.get()); // Sets Reservation with persisted Member.
            } else {
                Member savedMember = memberRepository.save(incomingMember); // Creates a new row in memberRepository.
                reservation.setMember(savedMember); // Sets Reservation with newly persisted Member.
            }
        }

        // Creates a new row in reservationRepository.
        return reservationRepository.save(reservation);
    }

    // READ
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long reservationId) {
        return reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservationId));
    }

    // UPDATE
    public Reservation updateReservationById(Reservation reservation, Long reservationId) {

        // Checks for persisted Reservation.
        Reservation existingReservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservationId));

        if (reservation.getStatus() != null) {
            existingReservation.setStatus(reservation.getStatus()); // Sets persisted Reservation with updated status.
        }

        if (reservation.getStartDate() != null) {
            existingReservation.setStartDate(reservation.getStartDate()); // Sets persisted Reservation with updated startDate.
        }

        if (reservation.getEndDate() != null) {
            existingReservation.setEndDate(reservation.getEndDate()); // Sets persisted Reservation with updated endDate.
        }

        // Creates a new row in reservationRepository.
        return reservationRepository.save(existingReservation);
    }

    // DELETE
    public void deleteReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
