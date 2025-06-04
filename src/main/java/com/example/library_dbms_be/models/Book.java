package com.example.library_dbms_be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean availability;
    private String author_name;
    private String title;
    private Long cover;
    private Long author_key;
    private Long key;
    private Long firstPublishYear;

    // cascade deletes/updates reservations automatically when a member is removed
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCover() {
        return cover;
    }

    public void setCover(Long cover) {
        this.cover = cover;
    }

    public Long getAuthor_key() {
        return author_key;
    }

    public void setAuthor_key(Long author_key) {
        this.author_key = author_key;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Long getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(Long firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
