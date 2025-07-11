package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.dtos.BookRequestDTO;
import com.example.library_dbms_be.dtos.BookResponseDTO;
import com.example.library_dbms_be.mappers.BookMapper;
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
    public BookResponseDTO createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    // READ
    @GetMapping // ("/api/books")
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}") // ("/api/books/{bookId}")
    public BookResponseDTO getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    // UPDATE
    @PutMapping("/{bookId}") // ("/api/books/{bookId}")
    public BookResponseDTO updateBookById(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.updateBookById(bookId, bookRequestDTO);
    }

    // DELETE
    @DeleteMapping("/{bookId}") // ("/api/books/{bookId}")
    public String deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return String.format("Book deleted with ID: " + bookId);
    }
}
