package com.example.library_dbms_be.repositories;

import com.example.library_dbms_be.enums.Availability;
import com.example.library_dbms_be.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByAvailabilityAndAuthorAndTitle(Availability availability, String author, String title);

}
