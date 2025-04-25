package org.example.service.impl;

import org.example.Models.Address;
import org.example.Models.User;
import org.example.dto.SignUpForm;
import org.example.repositories.AddressRepository;
import org.example.repositories.UserRepository;
import org.example.service.SignUpService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm signUpForm) throws SQLException {

        Address address = Address.builder()
                .street(signUpForm.getStreet())
                .city(signUpForm.getCity())
                .house(signUpForm.getHouse())
                .flat(signUpForm.getFlat())
                .build();
        addressRepository.save(address);
        Long addressid = addressRepository.findIdByAddress(address);

        User user = User.builder()
                .username(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .addressid(addressid)
                .build();
        userRepository.save(user);

    }

    @Override
    public boolean isUsernameExist(String username) {
        try {
            return userRepository.findByUsername(username).isPresent();
        } catch (SQLException e){
            return false;
        }
    }
}
