package com.moneybook.mapper;

import com.moneybook.dto.OperationCreationDto;
import com.moneybook.entity.*;

public class OperationMapper {

    public Operation mapOperationCreationDtoToEntity(
            OperationCreationDto operationCreationDto,
            Account account,
            ExpensePlan expensePlan,
            Envelope envelope,
            Category category
    ) {
        Operation operation = new Operation();
        operation.setAmount(operationCreationDto.getAmount());
        operation.setOperationDateTime(operationCreationDto.getOperationDateTime());
        operation.setComment(operationCreationDto.getComment());
        operation.setAccount(account);
        operation.setExpensePlan(expensePlan);
        operation.setEnvelope(envelope);
        operation.setCategory(category);

        return operation;
    }
}
