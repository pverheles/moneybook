package com.moneybook.entity;

import com.moneybook.constants.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account extends AmountEntity {

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String bank;

}
