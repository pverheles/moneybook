package com.moneybook.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

}
