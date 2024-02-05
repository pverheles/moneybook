package com.moneybook.entity;

import com.moneybook.constants.DBConstants;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseAmountEntity extends BaseOwnedEntity {

    @Column(nullable = false, scale = DBConstants.AMOUNT_SCALE, precision = DBConstants.AMOUNT_PRECISION)
    private BigDecimal amount;

}
