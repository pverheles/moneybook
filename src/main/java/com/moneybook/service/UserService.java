package com.moneybook.service;

import com.moneybook.dto.UserDto;
import com.moneybook.entity.User;
import com.moneybook.mapper.UserMapper;
import com.moneybook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createOrUpdateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        User user;
        if (userOptional.isEmpty()) {
            user = userMapper.mapUserDtoToUser(userDto);
        } else {
            user = userMapper.mapUserDtoToUser(userDto, userOptional.get());
        }
        userRepository.save(user);
        return userDto;
    }
}
