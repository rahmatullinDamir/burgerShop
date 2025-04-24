package org.example.service;

import org.example.dto.AddressDto;

import java.sql.SQLException;

public interface AddressService {
    AddressDto getAddressById(Long id) throws SQLException;
    void updateAddress(AddressDto addressDto);
}
