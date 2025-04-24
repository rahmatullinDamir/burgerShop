package org.example.service;


import org.example.dto.UserDto;

public interface ProfileService {
    UserDto getUserById(Long id);

    void updateUser(UserDto userDto);


}
