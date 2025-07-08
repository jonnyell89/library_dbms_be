package com.example.library_dbms_be.services;

import com.example.library_dbms_be.dtos.AddressRequestDTO;
import com.example.library_dbms_be.dtos.MemberRequestDTO;
import com.example.library_dbms_be.dtos.MemberResponseDTO;
import com.example.library_dbms_be.mappers.MemberMapper;
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
    public MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO) {

        // Maps the MemberRequestDTO to a Member object.
        Member member = MemberMapper.toModel(memberRequestDTO);

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
        Member savedMember = memberRepository.save(member);

        // Maps the Member object to a MemberResponseDTO.
        return MemberMapper.toMemberResponseDTO(savedMember);
    }

    // READ
    public List<MemberResponseDTO> getAllMembers() {

        return memberRepository
                .findAll()
                .stream()
                .map(MemberMapper::toMemberResponseDTO)
                .toList();
    } // Converting Member objects to MemberResponseDTOs -> Stream added with help from ChatGPT

    public MemberResponseDTO getMemberById(Long memberId) {

        // Checks for persisted Member.
        Member member = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with memberId: " + memberId));

        // Maps the Member object to a MemberResponseDTO.
        return MemberMapper.toMemberResponseDTO(member);
    }

    // GET http://localhost:8080/api/members/search?name=Jonny&email=jonny@email.com
    public MemberResponseDTO getMemberByNameAndEmail(String name, String email) {

        // Checks for persisted Member.
        Member member = memberRepository
                .findByNameAndEmail(name, email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Member not found with name: %s, and email: %s", name, email)));

        // Maps the Member object to a MemberResponseDTO.
        return MemberMapper.toMemberResponseDTO(member);
    }

    // UPDATE
    public MemberResponseDTO updateMemberById(Long memberId, MemberRequestDTO memberRequestDTO) {

        // Checks for persisted Member.
        Member existingMember = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with memberId: " + memberId));

        if (memberRequestDTO.getName() != null && !memberRequestDTO.getName().trim().isEmpty()) {
            existingMember.setName(memberRequestDTO.getName()); // Sets persisted Member with updated name.
        }

        if (memberRequestDTO.getEmail() != null && !memberRequestDTO.getEmail().trim().isEmpty()) {
            existingMember.setEmail(memberRequestDTO.getEmail()); // Sets persisted Member with updated email.
        }

        // Handles MemberRequestDTO object's associated AddressRequestDTO object.
        AddressRequestDTO incomingAddressRequestDTO = memberRequestDTO.getAddress();

        if (incomingAddressRequestDTO != null) {
            // Checks for persisted Address.
            Optional<Address> existingAddress = addressRepository.findByLine1AndPostcode(
                    incomingAddressRequestDTO.getLine1(),
                    incomingAddressRequestDTO.getPostcode()
            );

            if (existingAddress.isPresent()) {
                existingMember.setAddress(existingAddress.get()); // Sets persisted Member with persisted Address.
            } else {
                Address newAddress = new Address();
                newAddress.setLine1(incomingAddressRequestDTO.getLine1());
                newAddress.setLine2(incomingAddressRequestDTO.getLine2());
                newAddress.setCity(incomingAddressRequestDTO.getCity());
                newAddress.setCounty(incomingAddressRequestDTO.getCounty());
                newAddress.setPostcode(incomingAddressRequestDTO.getPostcode());

                Address savedAddress = addressRepository.save(newAddress); // Creates a new row in addressRepository.
                existingMember.setAddress(savedAddress); // Sets persisted Member with newly persisted Address.
            }
        }

        // Creates a new row, or updates an existing row, in memberRepository.
        Member updatedMember = memberRepository.save(existingMember);

        // Maps the Member object to a MemberResponseDTO.
        return MemberMapper.toMemberResponseDTO(updatedMember);
    }

    // DELETE
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
