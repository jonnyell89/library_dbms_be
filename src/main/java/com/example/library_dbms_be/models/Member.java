package com.example.library_dbms_be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Members")
public class Member {

    @Id // Assigns PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false) // Assigns FOREIGN KEY
    @JsonIgnoreProperties("members") // Prevents infinite JSON loops.
    private Address address;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Cascade deletes/updates reservations automatically when a member is removed.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore // Prevents recursive conflict with Reservation.member
    private List<Reservation> reservations;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}'; // Reference to address and reservations removed to prevent recursive conflict.
    }
}
