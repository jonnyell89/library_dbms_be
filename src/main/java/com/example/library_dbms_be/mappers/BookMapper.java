package com.example.library_dbms_be.mappers;

import com.example.library_dbms_be.dtos.BookRequestDTO;
import com.example.library_dbms_be.dtos.BookResponseDTO;
import com.example.library_dbms_be.models.Book;

public class BookMapper {

    public static Book toModel(BookRequestDTO bookRequestDTO) {

        Book book = new Book();
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setTitle((bookRequestDTO.getTitle()));
        book.setAuthorKey(bookRequestDTO.getAuthorKey());
        book.setTitleKey(bookRequestDTO.getTitleKey());
        book.setFirstPublishYear(bookRequestDTO.getFirstPublishYear());
        book.setCover(bookRequestDTO.getCover());
        book.setCoverEditionKey((bookRequestDTO.getCoverEditionKey()));

        return book;
    }

    public static BookResponseDTO toBookResponseDTO(Book book) {

        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setBookId(book.getBookId());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthorKey(book.getAuthorKey());
        bookResponseDTO.setTitleKey(book.getTitleKey());
        bookResponseDTO.setFirstPublishYear(book.getFirstPublishYear());
        bookResponseDTO.setCover(book.getCover());
        bookResponseDTO.setCoverEditionKey(book.getCoverEditionKey());

        return bookResponseDTO;
    }
}
