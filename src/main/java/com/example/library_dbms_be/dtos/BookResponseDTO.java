package com.example.library_dbms_be.dtos;

import com.example.library_dbms_be.enums.Availability;

public class BookResponseDTO {
    private Long bookId;
    private Availability availability;
    private String author;
    private String title;
    private Integer cover;

    public BookResponseDTO() {};

    public BookResponseDTO(Long bookId, Availability availability, String author, String title, Integer cover) {
        this.bookId = bookId;
        this.availability = availability;
        this.author = author;
        this.title = title;
        this.cover = cover;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "BookResponseDTO{" +
                "bookId=" + bookId +
                ", availability=" + availability +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", cover=" + cover +
                '}';
    }
}
