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

        Address incomingAddress = member.getAddress();

        // may or may not contain a null value
        Optional<Address> existingAddress = addressRepository.findByLine1AndPostcode(
                incomingAddress.getLine1(),
                incomingAddress.getPostcode()
        );

        if (existingAddress.isPresent()) {
            member.setAddress(existingAddress.get());
        } else {
            addressRepository.save(incomingAddress);
        }

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

    // DELETE
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
