package com.example.library_dbms_be.dtos;

import java.time.LocalDate;

public class ReservationRequestDTO {
    private Long memberId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationRequestDTO() {};

    public ReservationRequestDTO(Long memberId, LocalDate startDate, LocalDate endDate) {
        this.memberId = memberId;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return "ReservationRequestDTO{" +
                "memberId=" + memberId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
