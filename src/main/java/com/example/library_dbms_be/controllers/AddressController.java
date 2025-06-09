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

    @GetMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public Address getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    // UPDATE
    @PutMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public Address updateAddressById(@PathVariable Long addressId, @RequestBody Address address) {
        return addressService.updateAddressById(addressId, address);
    }

    // DELETE
    @DeleteMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public String deleteAddressById(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        return String.format("Address deleted with ID: " + addressId);
    }
}
