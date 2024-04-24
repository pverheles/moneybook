package com.moneybook.dto;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class AccountDto {

    @NotNull
    private BigDecimal amount;

    @NotEmpty
    private String name;

    @NotNull
    private Bank bank;

    @NotNull
    private Currency currency;

}
