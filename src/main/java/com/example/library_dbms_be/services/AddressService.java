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

        if (Objects.nonNull(address.getLine1()) && !"".equalsIgnoreCase(address.getLine1())) {
            existingAddress.setLine1(address.getLine1());
        }

        if (Objects.nonNull(address.getLine2()) && !"".equalsIgnoreCase(address.getLine2())) {
            existingAddress.setLine2(address.getLine2());
        }

        if (Objects.nonNull(address.getCity()) && !"".equalsIgnoreCase(address.getCity())) {
            existingAddress.setCity(address.getCity());
        }

        if (Objects.nonNull(address.getCounty()) && !"".equalsIgnoreCase(address.getCounty())) {
            existingAddress.setCounty(address.getCounty());
        }

        if (Objects.nonNull(address.getPostcode()) && !"".equalsIgnoreCase(address.getPostcode())) {
            existingAddress.setPostcode(address.getPostcode());
        }

        return addressRepository.save(existingAddress);
    }

    // DELETE
    public void deleteAddressById(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
