package com.moneybook.dto;

import com.moneybook.constants.State;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryReadDto extends CategoryDto {

    private long id;

    private State state;
}
