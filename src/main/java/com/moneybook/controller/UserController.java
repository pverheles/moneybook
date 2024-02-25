package com.moneybook.controller;

import com.moneybook.dto.UserDto;
import com.moneybook.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Parameter(in = ParameterIn.HEADER,name = "email", required = true, content = @Content(schema = @Schema(type = "string")))
    public UserDto createOrUpdateUser(@Valid @RequestBody UserDto userDto) {
        return userService.createOrUpdateUser(userDto);
    }

}
