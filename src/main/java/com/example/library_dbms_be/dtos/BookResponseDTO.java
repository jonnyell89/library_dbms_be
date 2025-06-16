package com.example.library_dbms_be.dtos;

import com.example.library_dbms_be.enums.Availability;

public class BookResponseDTO {
    private Long bookId;
    private String author;
    private String title;
    private String authorKey;
    private String titleKey;
    private Integer firstPublishYear;
    private Integer cover;

    public BookResponseDTO() {};

    public BookResponseDTO(Long bookId, String author, String title, String authorKey, String titleKey, Integer firstPublishYear, Integer cover) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.authorKey = authorKey;
        this.titleKey = titleKey;
        this.firstPublishYear = firstPublishYear;
        this.cover = cover;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "BookResponseDTO{" +
                "bookId=" + bookId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", authorKey='" + authorKey + '\'' +
                ", titleKey='" + titleKey + '\'' +
                ", firstPublishYear=" + firstPublishYear +
                ", cover=" + cover +
                '}';
    }
}
