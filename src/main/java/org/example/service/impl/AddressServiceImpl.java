package org.example.service.impl;

import org.example.Models.Address;
import org.example.dto.AddressDto;
import org.example.repositories.AddressRepository;
import org.example.service.AddressService;

import java.sql.SQLException;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDto getAddressById(Long id)  {
        Optional<Address> OptionalAddress = null;
        try {
            OptionalAddress = addressRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (OptionalAddress.isPresent()) {
            Address address = OptionalAddress.get();
            return AddressDto.builder()
                    .id(address.getId())
                    .street(address.getStreet())
                    .city(address.getCity())
                    .house(address.getHouse())
                    .flat(address.getFlat())
                    .build();
        }return null;
    }

    @Override
    public void updateAddress(AddressDto addressDto) {
        Address address = Address.builder()
                .id(addressDto.getId())
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .house(addressDto.getHouse())
                .flat(addressDto.getFlat())
                .build();
        try {
            addressRepository.update(address);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
