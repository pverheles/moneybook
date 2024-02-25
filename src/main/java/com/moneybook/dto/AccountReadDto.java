package com.moneybook.dto;

import com.moneybook.constants.State;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountReadDto extends AccountDto {

    private long id;

    private State state;
}
