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
        return bookService.getAllBooks()
                .stream()
                .map(BookMapper::toBookResponseDTO)
                .toList();
    } // Converting Book objects to DTOs -> Stream added with help from ChatGPT

    @GetMapping("/{bookId}") // ("/api/books/{bookId}")
    public BookResponseDTO getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        return BookMapper.toBookResponseDTO(book);
    }

    // UPDATE
    @PutMapping("/{bookId}") // ("/api/books/{bookId}")
    public BookResponseDTO updateBookById(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO) {
        Book book = bookService.updateBookById(bookId, bookRequestDTO);
        return BookMapper.toBookResponseDTO(book);
    }

    // DELETE
    @DeleteMapping("/{bookId}") // ("/api/books/{bookId}")
    public String deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return String.format("Book deleted with ID: " + bookId);
    }
}
