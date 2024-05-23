package com.moneybook.entity;

import com.moneybook.constants.OperationType;
import jakarta.persistence.AttributeConverter;

public class OperationCodeConverter implements AttributeConverter<OperationType, Character> {
    @Override
    public Character convertToDatabaseColumn(OperationType operationType) {
        return operationType.getCode();
    }

    @Override
    public OperationType convertToEntityAttribute(Character operationTypeCode) {
        return operationTypeCode == OperationType.Constants.EXPENSE_CODE ? OperationType.EXPENSE : OperationType.INCOME;
    }
}
