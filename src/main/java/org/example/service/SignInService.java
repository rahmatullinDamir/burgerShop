package org.example.service;

import org.example.dto.SignInForm;
import org.example.dto.UserDto;

import java.sql.SQLException;

public interface SignInService {

    UserDto signIn(SignInForm signInForm);

    Boolean checkPassword(String rawPassword, String encodedPassword);
}
