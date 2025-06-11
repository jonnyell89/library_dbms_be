package com.example.library_dbms_be.mappers;

import com.example.library_dbms_be.dtos.AddressRequestDTO;
import com.example.library_dbms_be.dtos.AddressResponseDTO;
import com.example.library_dbms_be.models.Address;

public class AddressMapper {

    public static Address toModel(AddressRequestDTO addressRequestDTO) {

        Address address = new Address();
        address.setLine1(addressRequestDTO.getLine1());
        address.setLine2(addressRequestDTO.getLine2());
        address.setCity(addressRequestDTO.getCity());
        address.setCounty(addressRequestDTO.getCounty());
        address.setPostcode(addressRequestDTO.getPostcode());

        return address;
    }

    public static AddressResponseDTO toAddressResponseDTO(Address address) {

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        addressResponseDTO.setAddressId(address.getAddressId());
        addressResponseDTO.setLine1(address.getLine1());
        addressResponseDTO.setLine2(address.getLine2());
        addressResponseDTO.setCity(address.getCity());
        addressResponseDTO.setCounty(address.getCounty());
        addressResponseDTO.setPostcode(address.getPostcode());

        return addressResponseDTO;
    }
}
