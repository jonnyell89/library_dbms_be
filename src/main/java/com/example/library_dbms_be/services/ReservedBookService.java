package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.models.ReservedBook;
import com.example.library_dbms_be.repositories.BookRepository;
import com.example.library_dbms_be.repositories.ReservationRepository;
import com.example.library_dbms_be.repositories.ReservedBookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservedBookService {

    public final ReservedBookRepository reservedBookRepository;
    public final ReservationRepository reservationRepository;
    public final BookRepository bookRepository;

    ReservedBookService(ReservedBookRepository reservedBookRepository, ReservationRepository reservationRepository, BookRepository bookRepository) {
        this.reservedBookRepository = reservedBookRepository;
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
    }

    // CREATE
    public ReservedBook createReservedBook(ReservedBook reservedBook) {

        // Handles ReservedBook object's associated Reservation object.
        Reservation incomingReservation = reservedBook.getReservation();

        // Checks for persisted Reservation.
        Optional<Reservation> existingReservation = reservationRepository.findByStatusAndStartDateAndEndDate(
                incomingReservation.getStatus(),
                incomingReservation.getStartDate(),
                incomingReservation.getEndDate()
        );

        if (existingReservation.isPresent()) {
            reservedBook.setReservation(existingReservation.get()); // Sets ReservedBook with persisted Reservation.
        } else {
            Reservation savedReservation = reservationRepository.save(incomingReservation); // Creates a new row in reservationRepository.
            reservedBook.setReservation(savedReservation); // Sets ReservedBook with newly persisted Reservation.
        }

        // Handles ReservedBook object's associated Book object.
        Book incomingBook = reservedBook.getBook();

        // Checks for persisted Book.
        Optional<Book> existingBook = bookRepository.findByAvailabilityAndAuthorAndTitle(
                incomingBook.getAvailability(),
                incomingBook.getAuthorKey(),
                incomingBook.getTitle()
        );

        if (existingBook.isPresent()) {
            reservedBook.setBook(existingBook.get()); // Sets ReservedBook with persisted Book.
        } else {
            Book savedBook = bookRepository.save(incomingBook); // Creates a new row in bookRepository.
            reservedBook.setBook(savedBook); // Sets ReservedBook with newly persisted Book.
        }

        // Creates a new row in reservedBookRepository.
        return reservedBookRepository.save(reservedBook);
    }


    // READ



    // UPDATE



    // DELETE


}
