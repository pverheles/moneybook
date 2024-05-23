package com.moneybook.constants;

import lombok.Getter;

@Getter
public enum OperationType {

    EXPENSE(Constants.EXPENSE_CODE), INCOME(Constants.INCOME_CODE);

    private final Character code;

    OperationType(Character code) {
        this.code = code;
    }

    public static class Constants {
        public static final char EXPENSE_CODE = 'E';
        public static final char INCOME_CODE = 'I';
    }
}
