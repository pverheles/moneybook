package com.moneybook.controller;

import com.moneybook.dto.UserDto;
import com.moneybook.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createOrUpdateUser(UserDto userDto) {
        userService.createOrUpdateUser(userDto);
    }

}
