package com.moneybook.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreationDto {

    @NonNull
    private String name;

    private String comment;
}
