package com.example.library_dbms_be.dtos;

public class ReservedBookResponseDTO {
    private Long reservedBookId;
    private Long reservationId;
    private Long bookId;

    public ReservedBookResponseDTO() {};

    public ReservedBookResponseDTO(Long reservedBookId, Long reservationId, Long bookId) {
        this.reservedBookId = reservedBookId;
        this.reservationId = reservationId;
        this.bookId = bookId;
    }

    public Long getReservedBookId() {
        return reservedBookId;
    }

    public void setReservedBookId(Long reservedBookId) {
        this.reservedBookId = reservedBookId;
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
        return "ReservedBookResponseDTO{" +
                "reservedBookId=" + reservedBookId +
                ", reservationId=" + reservationId +
                ", bookId=" + bookId +
                '}';
    }
}
