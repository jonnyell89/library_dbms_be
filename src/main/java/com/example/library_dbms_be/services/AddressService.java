package com.example.library_dbms_be.services;

import com.example.library_dbms_be.models.Address;
import com.example.library_dbms_be.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressService {

    public final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // CREATE
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // READ
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long addressId) {
        return addressRepository
                .findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with addressId: " + addressId));
    }

    // UPDATE
    public Address updateAddressById(Address address, Long addressId) {

        Address existingAddress = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(("Address not found with addressId: " + addressId)));

        if (address.getLine1() != null && !address.getLine1().trim().isEmpty()) {
            existingAddress.setLine1(address.getLine1().trim());
        }

        if (address.getLine2() != null && !address.getLine2().trim().isEmpty()) {
            existingAddress.setLine2(address.getLine2().trim());
        }

        if (address.getCity() != null && !address.getCity().trim().isEmpty()) {
            existingAddress.setCity(address.getCity().trim());
        }

        if (address.getCounty() != null && !address.getCounty().trim().isEmpty()) {
            existingAddress.setCounty(address.getCounty().trim());
        }

        if (address.getPostcode() != null && !address.getPostcode().trim().isEmpty()) {
            existingAddress.setPostcode(address.getPostcode().trim());
        }

//        if (Objects.nonNull(address.getPostcode()) && !"".equalsIgnoreCase(address.getPostcode())) {
//            existingAddress.setPostcode(address.getPostcode());
//        }

        return addressRepository.save(existingAddress);
    }

    // DELETE
    public void deleteAddressById(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
