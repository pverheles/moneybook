package com.moneybook.entity;

import com.moneybook.constants.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class IncomeSource extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
