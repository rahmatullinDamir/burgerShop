package org.example.service;

import org.example.dto.SignUpForm;

import java.sql.SQLException;

public interface SignUpService {

    void signUp(SignUpForm signUpForm) throws SQLException;
    boolean isUsernameExist(String username);
}
