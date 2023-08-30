package com.moneybook.entity;

import com.moneybook.constants.Currency;

import java.math.BigDecimal;

public interface Summable {

    BigDecimal getAmount();
    Currency getCurrency();

}
