package com.moneybook.entity;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account extends AmountEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private Bank bank;

}
