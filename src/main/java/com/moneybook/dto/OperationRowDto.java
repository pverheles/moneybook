package com.moneybook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OperationRowDto {

    private Long id;

    private BigDecimal amount;

    private String expensePlanName;

    private Long categoryId;

    private String categoryName;

    private LocalDateTime operationDateTime;

    private String comment;
}
