package com.moneybook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class CategoryDto {

    @NotNull
    private String name;

    private String comment;
}
