package com.moneybook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OperationCreationDto {

    @NotNull
    private long accountId;

    @NotNull
    private BigDecimal amount;

    private long expensePlanId;

    private long envelopeId;

    @NotNull
    private long categoryId;

    @NotNull
    private LocalDateTime operationDateTime;

    private String comment;

}
