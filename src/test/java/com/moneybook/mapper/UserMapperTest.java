package com.moneybook.mapper;

import com.moneybook.constants.State;
import com.moneybook.dto.UserDto;
import com.moneybook.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void mapUserCreationDtoToUser_shouldMapCorrectly() {
        UserDto dto = new UserDto();
        dto.setEmail("petro@gmail.com");
        dto.setName("Petro Best");

        User user = userMapper.mapUserDtoToUser(dto);

        assertThat(user.getName()).isEqualTo("Petro Best");
        assertThat(user.getEmail()).isEqualTo("petro@gmail.com");
        assertThat(user.getState()).isEqualTo(State.A);
    }

    @Test
    void mapAccountUpdateDtoToEntity_shouldMapCorrectly() {
        User user = new User();
        UserDto dto = new UserDto();
        dto.setName("my user updated");

        User mappedUser = userMapper.mapUserDtoToUser(dto, user);

        assertThat(mappedUser).isSameAs(user);
        assertThat(mappedUser.getName()).isEqualTo("my user updated");
    }
}