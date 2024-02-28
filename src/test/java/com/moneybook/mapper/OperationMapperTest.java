package com.moneybook.mapper;

import com.moneybook.dto.OperationCreationDto;
import com.moneybook.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class OperationMapperTest {

    OperationMapper operationMapper;

    @BeforeEach
    void setUp() {
        operationMapper = new OperationMapper();
    }

    @Test
    void mapOperationCreationDtoToEntity_shouldMapCorrectly() {
        OperationCreationDto operationCreationDto = new OperationCreationDto();
        LocalDateTime operationDateTime = LocalDateTime.of(2024, Month.MARCH, 2, 22, 25, 32);
        operationCreationDto.setOperationDateTime(operationDateTime);
        BigDecimal amount = new BigDecimal("25.58");
        operationCreationDto.setAmount(amount);
        String comment = "my comment";
        operationCreationDto.setComment(comment);

        Account account = new Account();
        ExpensePlan expensePlan = new ExpensePlan();
        Envelope envelope = new Envelope();
        Category category = new Category();
        User user = new User();

        Operation operation = operationMapper.mapOperationCreationDtoToEntity(operationCreationDto,
                account, expensePlan, envelope, category, user);

        assertThat(operation.getAmount()).isEqualTo(amount);
        assertThat(operation.getOperationDateTime()).isEqualTo(operationDateTime);
        assertThat(operation.getComment()).isEqualTo(comment);
        assertThat(operation.getAccount()).isSameAs(account);
        assertThat(operation.getExpensePlan()).isSameAs(expensePlan);
        assertThat(operation.getEnvelope()).isSameAs(envelope);
        assertThat(operation.getCategory()).isSameAs(category);
        assertThat(operation.getUser()).isSameAs(user);

    }
}