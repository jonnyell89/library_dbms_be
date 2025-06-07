package com.example.library_dbms_be.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "ReservedBooks")
public class ReservedBook {

    @Id // Assigns PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserved_book_id")
    private Long reservedBookId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false) // Assigns FOREIGN KEY
    @JsonIgnoreProperties("reservedBooks") // Prevents infinite JSON loops.
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false) // Assigns FOREIGN KEY
    @JsonIgnoreProperties("reservedBooks") // Prevents infinite JSON loops.
    private Book book;

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
                '}'; // Reference to reservation and book removed to prevent recursive conflict.
    }
}
