package org.example.service.impl;

import org.example.Models.User;
import org.example.dto.SignInForm;
import org.example.dto.UserDto;
import org.example.repositories.UserRepository;
import org.example.service.SignInService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public SignInServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }


    @Override
    public UserDto signIn(SignInForm signInForm) throws SQLException {
        Optional<User> userOptional = userRepository.findByUsername(signInForm.getUsername());
        System.out.println(userOptional.isPresent());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (checkPassword(signInForm.getPassword(), user.getPassword())) {
                return UserDto.builder().
                        id(user.getId()).
                        username(user.getUsername()).
                        role(user.getRole()).
                        build();
            }
        }
        return null;
    }

    @Override
    public Boolean checkPassword(String rawPassword, String encodedPassword) throws SQLException {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
