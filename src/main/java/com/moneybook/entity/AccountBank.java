package com.moneybook.entity;

public enum AccountBank {
    MONOBANK("bank.name.monobank"),
    PRIVATBANK("bank.name.privatbank");

    private final String displayName;

    AccountBank(String displayName) {
        this.displayName = displayName;
    }

}
