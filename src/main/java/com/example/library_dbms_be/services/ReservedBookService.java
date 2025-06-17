package com.example.library_dbms_be.services;

import com.example.library_dbms_be.dtos.ReservedBookRequestDTO;
import com.example.library_dbms_be.dtos.ReservedBookResponseDTO;
import com.example.library_dbms_be.mappers.ReservedBookMapper;
import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.models.ReservedBook;
import com.example.library_dbms_be.repositories.BookRepository;
import com.example.library_dbms_be.repositories.ReservationRepository;
import com.example.library_dbms_be.repositories.ReservedBookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ReservedBookResponseDTO createReservedBook(ReservedBookRequestDTO reservedBookRequestDTO) {

        // Checks for persisted Reservation.
        Reservation reservation = reservationRepository.findById(reservedBookRequestDTO.getReservationId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservedBookRequestDTO.getReservationId()));

        // Checks for persisted Book.
        Book book = bookRepository.findById(reservedBookRequestDTO.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + reservedBookRequestDTO.getBookId()));

        // Maps reservedBookRequestDTO to a reservedBook object.
        ReservedBook reservedBook = ReservedBookMapper.toModel(reservedBookRequestDTO, reservation, book);

        // Saves the ReservedBook object to the reservedBookRepository.
        ReservedBook savedReservedBook = reservedBookRepository.save(reservedBook);

        // Maps the ReservedBook object to a ReservedBookResponseDTO.
        return ReservedBookMapper.toReservedBookResponseDTO(savedReservedBook);
    }

//        // Handles ReservedBook object's associated Reservation object.
//        Reservation incomingReservation = reservedBook.getReservation();
//
//        if (incomingReservation != null && incomingReservation.getReservationId() != null) {
//            // Checks for persisted Reservation.
//            Reservation existingReservation = reservationRepository
//                    .findById(incomingReservation.getReservationId())
//                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + incomingReservation.getReservationId()));
//
//            reservedBook.setReservation(existingReservation); // Sets ReservedBook with persisted Reservation.
//        }
//
//        // Handles ReservedBook object's associated Book object.
//        Book incomingBook = reservedBook.getBook();
//
//        if (incomingBook != null && incomingBook.getBookId() != null) {
//            // Checks for persisted Book.
//            Book existingBook = bookRepository
//                    .findById(incomingBook.getBookId())
//                    .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + incomingBook.getBookId()));
//
//            reservedBook.setBook(existingBook); // Sets ReservedBook with persisted Book.
//        }
//
//        // Creates a new row in reservedBookRepository.
//        return reservedBookRepository.save(reservedBook);
//    }

    // READ
    public List<ReservedBookResponseDTO> getAllReservedBooks() {

        return reservedBookRepository.findAll()
                .stream()
                .map(ReservedBookMapper::toReservedBookResponseDTO)
                .toList();
    }

    public ReservedBookResponseDTO getReservedBookById(Long reservedBookId) {

        ReservedBook reservedBook = reservedBookRepository.findById(reservedBookId)
                .orElseThrow(() -> new EntityNotFoundException("ReservedBook not found with reservedBookId: " + reservedBookId));

        return ReservedBookMapper.toReservedBookResponseDTO(reservedBook);
    }


    // UPDATE
    public ReservedBookResponseDTO updateReservedBookById(Long reservedBookId, ReservedBookRequestDTO reservedBookRequestDTO) {

        // Checks for persisted ReservedBook.
        ReservedBook existingReservedBook = reservedBookRepository
                .findById(reservedBookId)
                .orElseThrow(() -> new EntityNotFoundException("ReservedBook not found with reservedBookId: " + reservedBookId));

        Reservation existingReservation = reservationRepository.findById(reservedBookRequestDTO.getReservationId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + reservedBookRequestDTO.getReservationId()));

        existingReservedBook.setReservation(existingReservation);

        Book existingBook = bookRepository.findById(reservedBookRequestDTO.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + reservedBookRequestDTO.getBookId()));

        existingReservedBook.setBook(existingBook);

        // Creates a new row, or updates an existing row, in reservedBookRepository.
        ReservedBook updatedReservedBook = reservedBookRepository.save(existingReservedBook);

        // Maps the ReservedBook object to a ReservedBookResponseDTO.
        return ReservedBookMapper.toReservedBookResponseDTO(updatedReservedBook);

//        // Handles ReservedBook object's associated Reservation object.
//        Reservation incomingReservation = reservedBook.getReservation();
//
//        if (incomingReservation != null && incomingReservation.getReservationId() != null) {
//            // Checks for persisted Reservation.
//            Reservation existingReservation = reservationRepository
//                    .findById(incomingReservation.getReservationId())
//                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found with reservationId: " + incomingReservation.getReservationId()));
//
//            existingReservedBook.setReservation(existingReservation); // Sets ReservedBook with persisted Reservation.
//        }
//
//        // Handles ReservedBook object's associated Book object.
//        Book incomingBook = reservedBook.getBook();
//
//        if (incomingBook != null && incomingBook.getBookId() != null) {
//            // Checks for persisted Book.
//            Book existingBook = bookRepository
//                    .findById(incomingBook.getBookId())
//                    .orElseThrow(() -> new EntityNotFoundException("Book not found with bookId: " + incomingBook.getBookId()));
//
//            existingReservedBook.setBook(existingBook); // Sets ReservedBook with persisted Book.
//        }
//
//        // Creates a new row, or updates an existing row, in reservedBookRepository.
//        return reservedBookRepository.save(existingReservedBook);
    }

    // DELETE
    public void deleteReservedBookById(Long reservedBookId) {
        reservedBookRepository.deleteById(reservedBookId);
    }
}
