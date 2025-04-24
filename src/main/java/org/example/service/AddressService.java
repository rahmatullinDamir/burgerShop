package org.example.service;

import org.example.dto.AddressDto;

public interface AddressService {
    AddressDto getAddress(String id);
    void updateAddress(AddressDto addressDto);
}
