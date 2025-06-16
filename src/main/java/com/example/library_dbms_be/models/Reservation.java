package com.example.library_dbms_be.models;

import com.example.library_dbms_be.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Reservations")
public class Reservation {

    @Id // Assigns PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "member_id") // Assigns FOREIGN KEY
    @JsonIgnoreProperties("reservations") // Prevents infinite JSON loops.
    private Member member;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false)
//    private Status status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // cascade deletes/updates reservations automatically when a member is removed.
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonIgnore // prevents conflict with ReservedBook.reservation
    List<ReservedBook> reservedBooks;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }

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

    public List<ReservedBook> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(List<ReservedBook> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
//                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}'; // Reference to member and reservedBooks removed to prevent recursive conflict.
    }
}
