package com.example.library_dbms_be.services;

import com.example.library_dbms_be.repositories.BookRepository;
import com.example.library_dbms_be.repositories.ReservationRepository;
import com.example.library_dbms_be.repositories.ReservedBookRepository;
import org.springframework.stereotype.Service;

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

    
}
