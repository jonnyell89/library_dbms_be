package com.example.library_dbms_be.repositories;

import com.example.library_dbms_be.models.ReservedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedBookRepository extends JpaRepository<ReservedBook, Long> {

}
