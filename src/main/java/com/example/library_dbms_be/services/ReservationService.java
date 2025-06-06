package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Address;
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

        Member incomingMember = reservation.getMember();

        if (incomingMember != null && incomingMember.getName() != null && incomingMember.getEmail() != null) {
            // Checks memberRepository for a match
            Optional<Member> existingMember = memberRepository.findByNameAndEmail(
                    incomingMember.getName(),
                    incomingMember.getEmail()
            );

            if (existingMember.isPresent()) {
                // Sets the already persisted member with an ID back to the reservation
                reservation.setMember(existingMember.get());
            } else {
                // Saves the member to the memberRepository, assigning it an ID.
                Member savedMember = memberRepository.save(incomingMember);
                // Sets the newly persisted member now with an ID back to the reservation
                reservation.setMember(savedMember);
            }
        }

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

        Reservation existingReservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservationId));

        if (reservation.getStatus() != null) {
            existingReservation.setStatus(reservation.getStatus());
        }

        if (reservation.getStartDate() != null) {
            existingReservation.setStartDate(reservation.getStartDate());
        }

        if (reservation.getEndDate() != null) {
            existingReservation.setEndDate(reservation.getEndDate());
        }

        return reservationRepository.save(existingReservation);
    }

    // DELETE
    public void deleteReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
