package com.example.library_dbms_be.repositories;

import com.example.library_dbms_be.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNameAndEmail(String name, String email);

}
