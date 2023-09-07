package com.moneybook.entity;


import com.moneybook.constants.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
public class MoneyAction extends AmountEntity implements Summable {

    @ManyToOne(optional = false)
    private Account account;

    @ManyToOne
    private Category category;

    @Override
    public Currency getCurrency() {
        return account.getCurrency();
    }

    // I - income, E - expense
    @Column
    private Character type;



}
