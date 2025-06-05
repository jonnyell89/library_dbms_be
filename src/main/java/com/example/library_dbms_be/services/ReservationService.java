package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.repositories.MemberRepository;
import com.example.library_dbms_be.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    public final ReservationRepository reservationRepository;
    public final MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
    }

    public Reservation createReservation(String memberId, String startDate) {

    }
}
