package org.example.service;

import org.example.dto.AddressDto;


public interface AddressService {
    AddressDto getAddressById(Long id);
    void updateAddress(AddressDto addressDto);
}
