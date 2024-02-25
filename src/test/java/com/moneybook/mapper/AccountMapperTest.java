package com.moneybook.mapper;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import com.moneybook.constants.State;
import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.dto.AccountUpdateDto;
import com.moneybook.entity.Account;
import com.moneybook.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
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

        User user = new User();
        user.setEmail("petro@mail.com");

        Account account = accountMapper.mapAccountCreationDtoToEntity(accountCreationDto, user);

        assertThat(account.getName()).isEqualTo("my account");
        assertThat(account.getAmount()).isEqualTo(new BigDecimal("200000"));
        assertThat(account.getBank()).isEqualTo(Bank.PRIVATBANK);
        assertThat(account.getCurrency()).isEqualTo(Currency.EUR);
        assertThat(account.getUser()).isSameAs(user);
        assertThat(account.getState()).isEqualTo(State.A);
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

    @Test
    void mapEntityToAccountReadDto_shouldMapCorrectly() {
        Account account = new Account();
        account.setName("test1");
        account.setId(3L);
        account.setState(State.A);
        account.setCurrency(Currency.USD);
        account.setAmount(BigDecimal.TEN);
        account.setBank(Bank.PRIVATBANK);

        AccountReadDto accountReadDto = accountMapper.mapEntityToAccountReadDto(account);

        AccountReadDto expectedAccountReadDto = new AccountReadDto();
        expectedAccountReadDto.setName("test1");
        expectedAccountReadDto.setId(3L);
        expectedAccountReadDto.setState(State.A);
        expectedAccountReadDto.setCurrency(Currency.USD);
        expectedAccountReadDto.setAmount(BigDecimal.TEN);
        expectedAccountReadDto.setBank(Bank.PRIVATBANK);

        assertThat(accountReadDto).isEqualTo(expectedAccountReadDto);

    }

}