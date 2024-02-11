package com.moneybook.service;

import com.moneybook.dto.UserDto;
import com.moneybook.entity.User;
import com.moneybook.mapper.UserMapper;
import com.moneybook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void createOrUpdateUser_whenNewUser_shouldCreate() {
        UserDto userDto = new UserDto();
        userDto.setEmail("petro@mail.com");
        userDto.setName("Petro Best");

        when(userRepository.findByEmail("petro@mail.com")).thenReturn(Optional.empty());

        User user = new User();
        user.setEmail("petro@mail.com");
        when(userMapper.mapUserDtoToUser(userDto)).thenReturn(user);

        userService.createOrUpdateUser(userDto);

        verify(userRepository).save(same(user));
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    void createOrUpdateUser_whenExistingUser_shouldUpdate() {
        UserDto userDto = new UserDto();
        userDto.setEmail("petro@mail.com");
        userDto.setName("Petro Best");

        User user = new User();
        user.setEmail("petro@mail.com");

        when(userRepository.findByEmail("petro@mail.com")).thenReturn(Optional.of(user));

        when(userMapper.mapUserDtoToUser(userDto, user)).thenReturn(user);

        userService.createOrUpdateUser(userDto);

        verify(userRepository).save(same(user));
        verifyNoMoreInteractions(userMapper);
    }
}