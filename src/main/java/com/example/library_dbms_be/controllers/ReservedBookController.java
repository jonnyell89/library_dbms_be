package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.dtos.ReservedBookRequestDTO;
import com.example.library_dbms_be.dtos.ReservedBookResponseDTO;
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
    public ReservedBookResponseDTO createReservedBook(@RequestBody ReservedBookRequestDTO reservedBookRequestDTO) {
        return reservedBookService.createReservedBook(reservedBookRequestDTO);
    }

    // READ
    @GetMapping // ("/api/reserved-books")
    public List<ReservedBookResponseDTO> getAllReservedBooks() {
        return reservedBookService.getAllReservedBooks();
    }

    @GetMapping("/{reservedBookId}") // ("/api/reserved-books/{reservedBookId}")
    public ReservedBookResponseDTO getReservedBookById(@PathVariable Long reservedBookId) {
        return reservedBookService.getReservedBookById(reservedBookId);
    }

    // UPDATE
    @PutMapping("/{reservedBookId}") // ("/api/reserved-books/{reservedBookId}")
    public ReservedBookResponseDTO updateReservedBook(@PathVariable Long reservedBookId, @RequestBody ReservedBookRequestDTO reservedBookRequestDTO) {
        return reservedBookService.updateReservedBookById(reservedBookId, reservedBookRequestDTO);
    }

    // DELETE
    @DeleteMapping("/{reservedBookId}") // ("/api/reserved-books/{reservedBookId}")
    public String deleteReservedBookById(@PathVariable Long reservedBookId) {
        reservedBookService.deleteReservedBookById(reservedBookId);
        return String.format("ReservedBook deleted with ID: " + reservedBookId);
    }
}
