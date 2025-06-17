package com.example.library_dbms_be.mappers;

import com.example.library_dbms_be.dtos.ReservationResponseDTO;
import com.example.library_dbms_be.dtos.ReservedBookRequestDTO;
import com.example.library_dbms_be.dtos.ReservedBookResponseDTO;
import com.example.library_dbms_be.models.Book;
import com.example.library_dbms_be.models.Reservation;
import com.example.library_dbms_be.models.ReservedBook;

public class ReservedBookMapper {

    public static ReservedBook toModel(ReservedBookRequestDTO reservedBookRequestDTO, Reservation reservation, Book book) {

        ReservedBook reservedBook = new ReservedBook();
        reservedBook.setReservation(reservation);
        reservedBook.setBook(book);

        return reservedBook;
    }

    public static ReservedBookResponseDTO toReservedBookResponseDTO(ReservedBook reservedBook) {

        ReservedBookResponseDTO reservedBookResponseDTO = new ReservedBookResponseDTO();
        reservedBookResponseDTO.setReservedBookId(reservedBook.getReservedBookId());
        reservedBookResponseDTO.setReservationId(reservedBook.getReservation().getReservationId());
        reservedBookResponseDTO.setBookId(reservedBook.getBook().getBookId());

        return reservedBookResponseDTO;
    }
}
