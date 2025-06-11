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
        return addressService.getAllAddresses()
                .stream()
                .map(AddressMapper::toAddressResponseDTO)
                .toList();
    } // Converting Address objects to DTOs -> Stream added with help from ChatGPT

    @GetMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public AddressResponseDTO getAddressById(@PathVariable Long addressId) {
        Address address = addressService.getAddressById(addressId);
        return AddressMapper.toAddressResponseDTO(address);
    }

    // UPDATE
    @PutMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public AddressResponseDTO updateAddressById(@PathVariable Long addressId, @RequestBody AddressRequestDTO addressRequestDTO) {
        Address address = addressService.updateAddressById(addressId, addressRequestDTO);
        return AddressMapper.toAddressResponseDTO(address);
    }

    // DELETE
    @DeleteMapping("/{addressId}") // ("/api/addresses/{addressId}")
    public String deleteAddressById(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        return String.format("Address deleted with ID: " + addressId);
    }
}
