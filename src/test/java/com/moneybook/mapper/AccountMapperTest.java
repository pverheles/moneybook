package com.moneybook.mapper;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountUpdateDto;
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
    void mapAccountCreationDtoToEntity_shouldMapCorrectly() {
        AccountCreationDto accountCreationDto = new AccountCreationDto();
        accountCreationDto.setName("my account");
        accountCreationDto.setAmount(new BigDecimal("200000"));
        accountCreationDto.setBank(Bank.PRIVATBANK);
        accountCreationDto.setCurrency(Currency.EUR);

        Account account = accountMapper.mapAccountCreationDtoToEntity(accountCreationDto);

        assertThat(account.getName()).isEqualTo("my account");
        assertThat(account.getAmount()).isEqualTo(new BigDecimal("200000"));
        assertThat(account.getBank()).isEqualTo(Bank.PRIVATBANK);
        assertThat(account.getCurrency()).isEqualTo(Currency.EUR);
    }

    @Test
    void mapAccountUpdateDtoToEntity_shouldMapCorrectly() {
        Account account = new Account();
        AccountUpdateDto accountUpdateDto = new AccountUpdateDto();
        accountUpdateDto.setName("my account updated");

        Account mappedAccount = accountMapper.mapAccountUpdateDtoToEntity(accountUpdateDto, account);

        assertThat(mappedAccount).isSameAs(account);
        assertThat(mappedAccount.getName()).isEqualTo("my account updated");
    }

    @Test
    void mapEntityToAccountIdDto_shouldMapCorrectly() {
        Account account = new Account();
        account.setId(5L);

        AccountIdDto accountIdDto = accountMapper.mapEntityToAccountIdDto(account);

        assertThat(accountIdDto.getId()).isEqualTo(5L);
    }

}