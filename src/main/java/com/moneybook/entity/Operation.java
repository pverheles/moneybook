package com.moneybook.entity;


import com.moneybook.constants.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "operation")
public class Operation extends AmountEntity implements Summable {

    @ManyToOne(optional = false)
    private Account account;

    @ManyToOne
    private Category category;

    @Override
    public Currency getCurrency() {
        return account.getCurrency();
    }

    @ManyToOne
    private ExpensePlan expensePlan;

    @ManyToOne
    private Envelope envelope;

}
