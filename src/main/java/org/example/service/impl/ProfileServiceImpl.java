package org.example.service.impl;

import org.example.Models.User;
import org.example.dto.UserDto;
import org.example.repositories.UserRepository;
import org.example.service.ProfileService;

import java.sql.SQLException;
import java.util.Optional;

public class ProfileServiceImpl implements ProfileService {

    private UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .build();
        }
        return null;

    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .build();
        userRepository.update(user);
    }
}
