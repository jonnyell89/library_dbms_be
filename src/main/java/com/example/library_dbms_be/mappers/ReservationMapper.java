package com.example.library_dbms_be.mappers;

import com.example.library_dbms_be.dtos.ReservationRequestDTO;
import com.example.library_dbms_be.dtos.ReservationResponseDTO;
import com.example.library_dbms_be.models.Member;
import com.example.library_dbms_be.models.Reservation;

public class ReservationMapper {

    public static Reservation toModel(ReservationRequestDTO reservationRequestDTO) {

        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationRequestDTO.getStartDate());
        reservation.setEndDate(reservationRequestDTO.getEndDate());

        return reservation;
    }

    public static ReservationResponseDTO toReservationResponseDTO(Reservation reservation) {

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();
        reservationResponseDTO.setReservationId(reservation.getReservationId());
        reservationResponseDTO.setMemberId(reservation.getMember().getMemberId());
        reservationResponseDTO.setStartDate(reservation.getStartDate());
        reservationResponseDTO.setEndDate(reservation.getEndDate());

        return reservationResponseDTO;
    }
}
