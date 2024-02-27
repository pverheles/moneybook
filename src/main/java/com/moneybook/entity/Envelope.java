package com.moneybook.entity;

import com.moneybook.constants.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "envelope")
public class Envelope extends BaseOwnedEntity {

    @ManyToOne
    private ExpensePlan expensePlan;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    private Category category;

}
