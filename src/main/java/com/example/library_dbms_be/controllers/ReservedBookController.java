package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.models.ReservedBook;
import com.example.library_dbms_be.services.ReservedBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserved-books")
public class ReservedBookController {

    private final ReservedBookService reservedBookService;

    ReservedBookController(ReservedBookService reservedBookService) {
        this.reservedBookService = reservedBookService;
    }

    // CREATE
    @PostMapping // ("/api/reserved-books")
    public ReservedBook createReservedBooks(@RequestBody ReservedBook reservedBook) {
        return reservedBookService.createReservedBook(reservedBook);
    }

    // READ
    @GetMapping // ("/api/reserved-books")
    public List<ReservedBook> getAllReservedBooks() {
        return reservedBookService.getAllReservedBooks();
    }

    @GetMapping("/{reservedBookId}") // ("/api/reserved-books/{reservedBookId}")
    public ReservedBook getReservedBookById(@PathVariable Long reservedBookId) {
        return reservedBookService.getReservedBookById(reservedBookId);
    }

    // UPDATE
    @PutMapping("/{reservedBookId}") // ("/api/reserved-books/{reservedBookId}")
    public ReservedBook updateReservedBook(@PathVariable Long reservedBookId, @RequestBody ReservedBook reservedBook) {
        return reservedBookService.updateReservedBookById(reservedBookId, reservedBook);
    }

    // DELETE
    @DeleteMapping("/{reservedBookId}") // ("/api/reserved-books/{reservedBookId}")
    public String deleteReservedBookById(@PathVariable Long reservedBookId) {
        reservedBookService.deleteReservedBookById(reservedBookId);
        return String.format("ReservedBook deleted with ID: " + reservedBookId);
    }
}
