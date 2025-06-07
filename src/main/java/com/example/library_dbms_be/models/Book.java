package com.example.library_dbms_be.models;

import com.example.library_dbms_be.enums.Availability;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Books")
public class Book {

    @Id // Assigns PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private Availability availability;

    @Column(name = "author", nullable = false)
    @JsonProperty("author_name")
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author_key", nullable = false)
    private String authorKey;

    @Column(name = "title_key", nullable = false)
    @JsonProperty("key")
    private String titleKey;

    @Column(name = "first_publish_year")
    private Integer firstPublishYear;

    @Column(name = "cover")
    @JsonProperty("cover_i")
    private Integer cover;

    // Cascade deletes/updates reservations automatically when a member is removed.
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore // Prevents recursive conflict with ReservedBook.book
    private List<ReservedBook> reservedBooks;

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

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public Integer getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(Integer firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public Integer getCover() {
        return cover;
    }

    public void setCover(Integer cover) {
        this.cover = cover;
    }

    public List<ReservedBook> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(List<ReservedBook> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", availability=" + availability +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", authorKey='" + authorKey + '\'' +
                ", titleKey='" + titleKey + '\'' +
                ", firstPublishYear=" + firstPublishYear +
                ", cover=" + cover +
                '}'; // Reference to reservedBooks removed to prevent recursive conflict.
    }
}
