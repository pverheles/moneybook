package com.moneybook.mapper;

import com.moneybook.dto.OperationCreationDto;
import com.moneybook.dto.OperationRowDto;
import com.moneybook.entity.*;

public class OperationMapper {

    public Operation mapOperationCreationDtoToEntity(
            OperationCreationDto operationCreationDto,
            Account account,
            ExpensePlan expensePlan,
            Envelope envelope,
            Category category,
            User user) {
        Operation operation = new Operation();
        operation.setAmount(operationCreationDto.getAmount());
        operation.setOperationDateTime(operationCreationDto.getOperationDateTime());
        operation.setComment(operationCreationDto.getComment());
        operation.setAccount(account);
        operation.setExpensePlan(expensePlan);
        operation.setEnvelope(envelope);
        operation.setCategory(category);
        operation.setUser(user);

        return operation;
    }

    public OperationRowDto mapOperationToOperationRowDto(Operation operation) {
        OperationRowDto operationRowDto = new OperationRowDto();
        operationRowDto.setId(operation.getId());
        operationRowDto.setCategoryId(operation.getCategory().getId());
        operationRowDto.setCategoryName(operation.getCategory().getName());
        operationRowDto.setExpensePlanName(operation.getExpensePlan().getName());
        operationRowDto.setAmount(operation.getAmount());
        operationRowDto.setComment(operation.getComment());
        operationRowDto.setOperationDateTime(operation.getOperationDateTime());
        return operationRowDto;
    }
}
