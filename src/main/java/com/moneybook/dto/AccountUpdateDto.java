package com.moneybook.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateDto {

    @NotEmpty
    private String name;

}
