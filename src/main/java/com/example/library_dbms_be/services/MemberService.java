package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Address;
import com.example.library_dbms_be.models.Member;
import com.example.library_dbms_be.repositories.AddressRepository;
import com.example.library_dbms_be.repositories.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    public final MemberRepository memberRepository;
    public final AddressRepository addressRepository;

    public MemberService(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    // CREATE
    public Member createMember(Member member) {

        // Handles Member object's associated Address object.
        Address incomingAddress = member.getAddress();

        // Checks for persisted Address.
        Optional<Address> existingAddress = addressRepository.findByLine1AndPostcode(
                incomingAddress.getLine1(),
                incomingAddress.getPostcode()
        );

        if (existingAddress.isPresent()) {
            member.setAddress(existingAddress.get()); // Sets Member with persisted Address.
        } else {
            Address savedAddress = addressRepository.save(incomingAddress); // Creates a new row in addressRepository.
            member.setAddress((savedAddress)); // Sets Member with newly persisted Address.
        }

        // Creates a new row in memberRepository.
        return memberRepository.save(member);
    }

    // READ
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with memberId: " + memberId));
    }

    // GET http://localhost:8080/api/members/search?name=Jonny&email=jonny@email.com
    public Member getMemberByNameAndEmail(String name, String email) {
        return memberRepository
                .findByNameAndEmail(name, email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Member not found with name: %s, and email: %s", name, email)));
    }

    // UPDATE
    public Member updateMemberById(Long memberId, Member member) {

        // Checks for persisted Member.
        Member existingMember = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with memberId: " + memberId));

        if (member.getName() != null && !member.getName().trim().isEmpty()) {
            existingMember.setName(member.getName()); // Sets persisted Member with updated name.
        }

        if (member.getEmail() != null && !member.getEmail().trim().isEmpty()) {
            existingMember.setEmail(member.getEmail()); // Sets persisted Member with updated email.
        }

        // Handles Member object's associated Address object.
        Address incomingAddress = member.getAddress();

        if (incomingAddress != null) {
            // Checks for persisted Address.
            Optional<Address> existingAddress = addressRepository.findByLine1AndPostcode(
                    incomingAddress.getLine1(),
                    incomingAddress.getPostcode()
            );

            if (existingAddress.isPresent()) {
                existingMember.setAddress(existingAddress.get()); // Sets persisted Member with persisted Address.
            } else {
                Address savedAddress = addressRepository.save(incomingAddress); // Creates a new row in addressRepository.
                existingMember.setAddress(savedAddress); // Sets persisted Member with newly persisted Address.
            }
        }

        // Creates a new row, or updates an existing row, in memberRepository.
        return memberRepository.save(existingMember);
    }

    // DELETE
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
