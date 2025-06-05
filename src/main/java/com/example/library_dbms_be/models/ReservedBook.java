package com.example.library_dbms_be.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "ReservedBooks")
public class ReservedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserved_book_id")
    private Long reservedBookId; // PRIMARY KEY

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    @JsonIgnoreProperties("reservedBooks") // prevents infinite JSON loops
    private Reservation reservation; // FOREIGN KEY

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties("reservedBooks") // prevents infinite JSON loops
    private Book book; // FOREIGN KEY

    public Long getReservedBookId() {
        return reservedBookId;
    }

    public void setReservedBookId(Long reservedBookId) {
        this.reservedBookId = reservedBookId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "ReservedBook{" +
                "reservedBookId=" + reservedBookId +
                '}';
    }
}
