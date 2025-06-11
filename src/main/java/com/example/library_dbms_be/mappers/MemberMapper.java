package com.example.library_dbms_be.mappers;

import com.example.library_dbms_be.dtos.AddressRequestDTO;
import com.example.library_dbms_be.dtos.MemberRequestDTO;
import com.example.library_dbms_be.dtos.MemberResponseDTO;
import com.example.library_dbms_be.models.Address;
import com.example.library_dbms_be.models.Member;

public class MemberMapper {

    public static Member toModel(MemberRequestDTO memberRequestDTO) {

        Address address = new Address();
        address.setLine1(memberRequestDTO.getAddress().getLine1());
        address.setLine2(memberRequestDTO.getAddress().getLine2());
        address.setCity(memberRequestDTO.getAddress().getCity());
        address.setCounty(memberRequestDTO.getAddress().getCounty());
        address.setPostcode(memberRequestDTO.getAddress().getPostcode());

        Member member = new Member();
        member.setName(memberRequestDTO.getName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setAddress(address);

        return member;
    }

    public static MemberResponseDTO toMemberResponseDTO(Member member) {

        AddressRequestDTO addressRequestDTO = new AddressRequestDTO();
        addressRequestDTO.setLine1(member.getAddress().getLine1());
        addressRequestDTO.setLine2(member.getAddress().getLine2());
        addressRequestDTO.setCity(member.getAddress().getCity());
        addressRequestDTO.setCounty(member.getAddress().getCounty());
        addressRequestDTO.setPostcode(member.getAddress().getPostcode());

        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setMemberId(member.getMemberId());
        memberResponseDTO.setName(member.getName());
        memberResponseDTO.setEmail(member.getEmail());
        memberResponseDTO.setAddress(addressRequestDTO);

        return memberResponseDTO;
    }
}
