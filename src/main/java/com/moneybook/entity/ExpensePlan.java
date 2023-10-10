package com.moneybook.entity;


import com.moneybook.constants.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "expense_plan")
public class ExpensePlan extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Currency currency;

}
