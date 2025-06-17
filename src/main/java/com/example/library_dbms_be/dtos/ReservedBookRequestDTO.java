package com.example.library_dbms_be.dtos;

public class ReservedBookRequestDTO {
    private Long reservationId;
    private Long bookId;

    public ReservedBookRequestDTO() {};

    public ReservedBookRequestDTO(Long reservationId, Long bookId) {
        this.reservationId = reservationId;
        this.bookId = bookId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "ReservedBookRequestDTO{" +
                "reservationId=" + reservationId +
                ", bookId=" + bookId +
                '}';
    }
}
