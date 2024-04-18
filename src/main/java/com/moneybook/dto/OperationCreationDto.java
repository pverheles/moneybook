package com.moneybook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationDateTime;

    private String comment;

}
