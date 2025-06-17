package com.example.library_dbms_be.dtos;

import java.time.LocalDate;

public class ReservationResponseDTO {
    private Long reservationId;
    private Long memberId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationResponseDTO() {};

    public ReservationResponseDTO(Long reservationId, Long memberId, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ReservationResponseDTO{" +
                "reservationId=" + reservationId +
                ", memberId=" + memberId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
