package com.example.library_dbms_be.dtos;

public class MemberResponseDTO {
    private Long memberId;
    private String name;
    private String email;
    private AddressRequestDTO address;

    public MemberResponseDTO() {};

    public MemberResponseDTO(Long memberId, String name, String email, AddressRequestDTO address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressRequestDTO getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberResponseDTO{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
