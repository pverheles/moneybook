package com.moneybook.mapper;

import com.moneybook.constants.State;
import com.moneybook.dto.UserDto;
import com.moneybook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserDtoToUser(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setState(State.A);

        return user;
    }

    public User mapUserDtoToUser(UserDto userDto, User user) {
        user.setName(userDto.getName());
        return user;
    }
}
