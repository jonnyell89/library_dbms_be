package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    public final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // READ
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + bookId));
    }

    // UPDATE
    public Book updateBookById(Book book, Long bookId) {

        Book existingBook = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(("Book not found with bookId: " + bookId)));

        if (book.getAvailability() != null) {
            existingBook.setAvailability(book.getAvailability());
        }

        if (book.getAuthor() != null && !book.getAuthor().trim().isEmpty()) {
            existingBook.setAuthor(book.getAuthor().trim());
        }

        if (book.getTitle() != null && !book.getTitle().trim().isEmpty()) {
            existingBook.setTitle(book.getTitle().trim());
        }

        if (book.getAuthorKey() != null && !book.getAuthorKey().trim().isEmpty()) {
            existingBook.setAuthorKey(book.getAuthorKey().trim());
        }

        if (book.getTitleKey() != null && !book.getTitleKey().trim().isEmpty()) {
            existingBook.setTitleKey(book.getTitleKey().trim());
        }

        if (book.getFirstPublishYear() != null) {
            existingBook.setFirstPublishYear(book.getFirstPublishYear());
        }

        if (book.getCover() != null) {
            existingBook.setCover(book.getCover());
        }

        return bookRepository.save(existingBook);
    }

    // DELETE
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
