package com.moneybook.entity;

import com.moneybook.constants.DBConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "expense_plan")
public class ExpensePlan extends BaseOwnedEntity {

    @Column(length = DBConstants.NAME_LENGTH)
    private String name;

}
