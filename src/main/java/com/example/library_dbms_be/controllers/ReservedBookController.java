package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.models.ReservedBook;
import com.example.library_dbms_be.services.ReservedBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservedbooks")
public class ReservedBookController {

    private final ReservedBookService reservedBookSerivce;

    ReservedBookController(ReservedBookService reservedBookService) {
        this.reservedBookSerivce = reservedBookService;
    }

    // CREATE
    @PostMapping // ("/api/reservedbooks")
    public ReservedBook createReservedBooks(@RequestBody ReservedBook reservedBook) {
        return reservedBookSerivce.createReservedBook(reservedBook);
    }

    // READ
    @GetMapping // ("/api/reservedbooks")
    public List<ReservedBook> getAllReservedBooks() {
        return reservedBookSerivce.getAllReservedBooks();
    }

    @GetMapping("/{id}") // ("/api/reservedbooks/{id}")
    public ReservedBook getReservedBookById(Long reservedBookId) {
        return reservedBookSerivce.getReservedBookById(reservedBookId);
    }

    // UPDATE

    // DELETE
    @DeleteMapping("/{id}") // ("/api/reservedbooks/{id}")
    public String deleteReservedBookById(@PathVariable Long reservedBookId) {
        reservedBookSerivce.deleteReservedBookById(reservedBookId);
        return String.format("ReservedBook deleted with ID: " + reservedBookId);
    }
}
