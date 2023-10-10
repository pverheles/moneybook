package com.moneybook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "envelope")
public class Envelope extends AmountEntity {

    private ExpensePlan expensePlan;

}
