package org.example.service.impl;

import org.example.Models.User;
import org.example.dto.SignUpForm;
import org.example.repositories.UserRepository;
import org.example.service.SignUpService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm signUpForm) throws SQLException {
        User user = User.builder()
                .username(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
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
