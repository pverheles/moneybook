package com.moneybook.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

}
