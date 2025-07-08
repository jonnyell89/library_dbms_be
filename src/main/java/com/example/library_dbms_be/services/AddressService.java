package com.example.library_dbms_be.services;

import com.example.library_dbms_be.dtos.AddressRequestDTO;
import com.example.library_dbms_be.dtos.AddressResponseDTO;
import com.example.library_dbms_be.mappers.AddressMapper;
import com.example.library_dbms_be.models.Address;
import com.example.library_dbms_be.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    public final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // CREATE
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {

        // Maps the addressRequestDTO to an Address object.
        Address address = AddressMapper.toModel(addressRequestDTO);

        // Saves the Address object to the addressRepository.
        Address savedAddress = addressRepository.save(address);

        // Maps the Address object to an AddressResponseDTO.
        return AddressMapper.toAddressResponseDTO(savedAddress);
    }

    // READ
    public List<AddressResponseDTO> getAllAddresses() {

        return addressRepository
                .findAll()
                .stream()
                .map(AddressMapper::toAddressResponseDTO)
                .toList();
    } // Converting Address objects to AddressResponseDTOs -> Stream added with help from ChatGPT

    public AddressResponseDTO getAddressById(Long addressId) {

        // Checks for persisted Address.
        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with addressId: " + addressId));

        // Maps the Address object to an AddressResponseDTO.
        return AddressMapper.toAddressResponseDTO(address);
    }

    // UPDATE
    public AddressResponseDTO updateAddressById(Long addressId, AddressRequestDTO addressRequestDTO) {

        // Checks for persisted Address.
        Address existingAddress = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(("Address not found with addressId: " + addressId)));

        if (addressRequestDTO.getLine1() != null && !addressRequestDTO.getLine1().trim().isEmpty()) {
            existingAddress.setLine1(addressRequestDTO.getLine1().trim()); // Sets persisted Address with updated line1.
        }

        if (addressRequestDTO.getLine2() != null && !addressRequestDTO.getLine2().trim().isEmpty()) {
            existingAddress.setLine2(addressRequestDTO.getLine2().trim()); // Sets persisted Address with updated line2.
        }

        if (addressRequestDTO.getCity() != null && !addressRequestDTO.getCity().trim().isEmpty()) {
            existingAddress.setCity(addressRequestDTO.getCity().trim()); // Sets persisted Address with updated city.
        }

        if (addressRequestDTO.getCounty() != null && !addressRequestDTO.getCounty().trim().isEmpty()) {
            existingAddress.setCounty(addressRequestDTO.getCounty().trim()); // Sets persisted Address with updated county.
        }

        if (addressRequestDTO.getPostcode() != null && !addressRequestDTO.getPostcode().trim().isEmpty()) {
            existingAddress.setPostcode(addressRequestDTO.getPostcode().trim()); // Sets persisted Address with updated postcode.
        }

//        if (Objects.nonNull(address.getPostcode()) && !"".equalsIgnoreCase(address.getPostcode())) {
//            existingAddress.setPostcode(address.getPostcode());
//        }

        // Creates a new row, or updates an existing row, in addressRepository.
        Address updatedAddress = addressRepository.save(existingAddress);

        // Maps the Address object to an AddressResponseDTO.
        return AddressMapper.toAddressResponseDTO(updatedAddress);
    }

    // DELETE
    public void deleteAddressById(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
