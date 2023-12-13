package com.moneybook.mapper;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import com.moneybook.dto.AccountDto;
import com.moneybook.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountMapperTest {

    AccountMapper accountMapper;

    @BeforeEach
    void setUp() {
        accountMapper = new AccountMapper();
    }

    @Test
    void mapDtoToEntity_shouldMapCorrectly() {
        AccountDto dto = new AccountDto();
        dto.setName("my account");
        dto.setAmount(new BigDecimal("200000"));
        dto.setBank(Bank.PRIVATBANK);
        dto.setCurrency(Currency.EUR);

        Account account = accountMapper.mapDtoToEntity(dto);

        assertThat(account.getName()).isEqualTo("my account");
        assertThat(account.getAmount()).isEqualTo(new BigDecimal("200000"));
        assertThat(account.getBank()).isEqualTo(Bank.PRIVATBANK);
        assertThat(account.getCurrency()).isEqualTo(Currency.EUR);
    }

}