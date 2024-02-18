package com.moneybook.controller;

import com.moneybook.dto.UserDto;
import com.moneybook.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createOrUpdateUser(@Valid @RequestBody UserDto userDto) {
        userService.createOrUpdateUser(userDto);
    }

}
