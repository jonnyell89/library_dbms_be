package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.models.ReservedBook;
import com.example.library_dbms_be.repositories.BookRepository;
import com.example.library_dbms_be.repositories.ReservationRepository;
import com.example.library_dbms_be.repositories.ReservedBookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservedBookService {

    public final ReservedBookRepository reservedBookRepository;
    public final ReservationRepository reservationRepository;
    public final BookRepository bookRepository;

    public ReservedBookService(ReservedBookRepository reservedBookRepository, ReservationRepository reservationRepository, BookRepository bookRepository) {
        this.reservedBookRepository = reservedBookRepository;
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
    }

    // CREATE
    public ReservedBook createReservedBook(ReservedBook reservedBook) {

        // Handles ReservedBook object's associated Reservation object.
        Reservation incomingReservation = reservedBook.getReservation();

        if (incomingReservation != null && incomingReservation.getReservationId() != null) {
            // Checks for persisted Reservation.
            Reservation existingReservation = reservationRepository
                    .findById(incomingReservation.getReservationId())
                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + incomingReservation.getReservationId()));

            reservedBook.setReservation(existingReservation); // Sets ReservedBook with persisted Reservation.
        }

        // Handles ReservedBook object's associated Book object.
        Book incomingBook = reservedBook.getBook();

        if (incomingBook != null && incomingBook.getBookId() != null) {
            // Checks for persisted Book.
            Book existingBook = bookRepository
                    .findById(incomingBook.getBookId())
                    .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + incomingBook.getBookId()));

            reservedBook.setBook(existingBook); // Sets ReservedBook with persisted Book.
        }

        // Creates a new row in reservedBookRepository.
        return reservedBookRepository.save(reservedBook);
    }

    // READ
    public List<ReservedBook> getAllReservedBooks() {
        return reservedBookRepository.findAll();
    }

    public ReservedBook getReservedBookById(Long reservedBookId) {
        return reservedBookRepository
                .findById(reservedBookId)
                .orElseThrow(() -> new EntityNotFoundException("ReservedBook not found with reservedBookId: " + reservedBookId));
    }


    // UPDATE
    public ReservedBook updateReservedBookById(ReservedBook reservedBook, Long reservedBookId) {

        ReservedBook existingReservedBook = reservedBookRepository
                .findById(reservedBookId)
                .orElseThrow(() -> new EntityNotFoundException("ReservedBook not found with reservedBookId: " + reservedBookId));

        // Handles ReservedBook object's associated Reservation object.
        Reservation incomingReservation = reservedBook.getReservation();

        if (incomingReservation != null && incomingReservation.getReservationId() != null) {
            // Checks for persisted Reservation.
            Reservation existingReservation = reservationRepository
                    .findById(incomingReservation.getReservationId())
                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + incomingReservation.getReservationId()));

            existingReservedBook.setReservation(existingReservation); // Sets ReservedBook with persisted Reservation.
        }

        // Handles ReservedBook object's associated Book object.
        Book incomingBook = reservedBook.getBook();

        if (incomingBook != null && incomingBook.getBookId() != null) {
            // Checks for persisted Book.
            Book existingBook = bookRepository
                    .findById(incomingBook.getBookId())
                    .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + incomingBook.getBookId()));

            existingReservedBook.setBook(existingBook); // Sets ReservedBook with persisted Book.
        }

        return reservedBookRepository.save(existingReservedBook);
    }

    // DELETE
    public void deleteReservedBookById(Long reservedBookId) {
        reservedBookRepository.deleteById(reservedBookId);
    }
}
