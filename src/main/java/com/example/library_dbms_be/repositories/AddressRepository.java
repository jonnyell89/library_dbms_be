package com.example.library_dbms_be.repositories;

import com.example.library_dbms_be.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByLine1AndPostcode(String line1, String postcode);

}
