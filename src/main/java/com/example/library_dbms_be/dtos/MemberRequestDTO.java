package com.example.library_dbms_be.dtos;

public class MemberRequestDTO {
    private String name;
    private String email;
    private AddressRequestDTO address;

    public MemberRequestDTO() {};

    public MemberRequestDTO(String name, String email, AddressRequestDTO address) {
        this.name = name;
        this.email = email;
        this.address = address;
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
        return "MemberRequestDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
