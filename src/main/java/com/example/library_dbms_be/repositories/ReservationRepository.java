package com.example.library_dbms_be.repositories;

import com.example.library_dbms_be.enums.Status;
import com.example.library_dbms_be.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByStatusAndStartDateAndEndDate(Status status, LocalDate startDate, LocalDate endDate);

}
