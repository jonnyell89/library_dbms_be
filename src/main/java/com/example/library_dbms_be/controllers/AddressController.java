package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.models.Address;
import com.example.library_dbms_be.services.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // CREATE
    @PostMapping // ("/api/addresses")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    // READ
    @GetMapping // ("/api/addresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/api/addresses/{id}")
    public Address getAddressById(Long addressId) {
        return addressService.getAddressById(addressId);
    }

    // UPDATE



    // DELETE
    @DeleteMapping("/api/addresses/{id}")
    public String deleteAddressById(Long addressId) {
        addressService.deleteAddressById(addressId);
        return String.format("Address deleted with ID: " + addressId);
    }
}
