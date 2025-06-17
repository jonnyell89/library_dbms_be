package com.example.library_dbms_be.services;

import com.example.library_dbms_be.dtos.ReservationRequestDTO;
import com.example.library_dbms_be.dtos.ReservationResponseDTO;
import com.example.library_dbms_be.mappers.ReservationMapper;
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
    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {

        // Checks for persisted Member.
        Long memberId = reservationRequestDTO.getMemberId();
        Member incomingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with memberID: " + memberId));

        // Maps the reservationRequestDTO to a Reservation object.
        Reservation reservation = ReservationMapper.toModel(reservationRequestDTO);

        // Sets Reservation with persisted Member.
        reservation.setMember(incomingMember);

        // Saves the Reservation object to the reservationRepository.
        Reservation savedReservation = reservationRepository.save(reservation);

        // Maps the Reservation object to a ReservationResponseDTO.
        return ReservationMapper.toReservationResponseDTO(savedReservation);
    }

    // READ
    public List<ReservationResponseDTO> getAllReservations() {

        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::toReservationResponseDTO)
                .toList();
    }

    public ReservationResponseDTO getReservationById(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservationId));

        return ReservationMapper.toReservationResponseDTO(reservation);
    }

    // UPDATE
    public ReservationResponseDTO updateReservationById(Long reservationId, ReservationRequestDTO reservationRequestDTO) {

        // Checks for persisted Reservation.
        Reservation existingReservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservationId));

//        if (reservation.getStatus() != null) {
//            existingReservation.setStatus(reservation.getStatus()); // Sets persisted Reservation with updated status.
//        }

        if (reservationRequestDTO.getStartDate() != null) {
            existingReservation.setStartDate(reservationRequestDTO.getStartDate()); // Sets persisted Reservation with updated startDate.
        }

        if (reservationRequestDTO.getEndDate() != null) {
            existingReservation.setEndDate(reservationRequestDTO.getEndDate()); // Sets persisted Reservation with updated endDate.
        }

        // Creates a new row, or updates an existing row, in reservationRepository.
        Reservation updatedReservation = reservationRepository.save(existingReservation);

        // Maps the Reservation object to a ReservationResponseDTO.
        return ReservationMapper.toReservationResponseDTO(updatedReservation);
    }

    // DELETE
    public void deleteReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
