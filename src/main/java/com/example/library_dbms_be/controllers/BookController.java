package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping // ("/api/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // READ
    @GetMapping // ("/api/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}") // ("/api/books/{id}")
    public Book getBookById(Long bookId) {
        return bookService.getBookById(bookId);
    }

    // UPDATE



    // DELETE
    @DeleteMapping("/{id}") // ("/api/books/{id}")
    public String deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return String.format("Book deleted with ID: " + bookId);
    }
}
