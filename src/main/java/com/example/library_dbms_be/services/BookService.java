package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // DELETE
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
