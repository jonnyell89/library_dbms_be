package com.example.library_dbms_be.controllers;

import com.example.library_dbms_be.dtos.AddressRequestDTO;
import com.example.library_dbms_be.dtos.AddressResponseDTO;
import com.example.library_dbms_be.mappers.AddressMapper;
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
    public AddressResponseDTO createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.createAddress(addressRequestDTO);
    }

    // READ
    @GetMapping // ("/api/addresses")
    public List<AddressResponseDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public AddressResponseDTO getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    // UPDATE
    @PutMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public AddressResponseDTO updateAddressById(@PathVariable Long addressId, @RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.updateAddressById(addressId, addressRequestDTO);
    }

    // DELETE
    @DeleteMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public String deleteAddressById(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        return String.format("Address deleted with ID: " + addressId);
    }
}
