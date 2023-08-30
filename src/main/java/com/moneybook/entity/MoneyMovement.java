package com.moneybook.entity;


import com.moneybook.constants.Currency;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class MoneyMovement extends AmountEntity implements Summable {

    @ManyToOne(optional = false)
    private Account account;

    @Override
    public Currency getCurrency() {
        return account.getCurrency();
    }
}
