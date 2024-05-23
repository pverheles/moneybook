package com.moneybook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moneybook.constants.OperationType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OperationCreationDto {

    @NotNull
    private Long accountId;

    @NotNull
    private BigDecimal amount;

    private Long expensePlanId;

    private Long envelopeId;

    @NotNull
    private Long categoryId;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime operationDateTime;

    private String comment;

    private OperationType type;

}
