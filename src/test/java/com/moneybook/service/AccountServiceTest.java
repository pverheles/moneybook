package com.moneybook.service;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.entity.Account;
import com.moneybook.mapper.AccountMapper;
import com.moneybook.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AccountServiceTest {

    AccountService accountService;

    @Mock
    AccountMapper accountMapper;

    @Mock
    AccountRepository accountRepository;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = openMocks(this);
        accountService = new AccountService(accountRepository, accountMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createAccount_shouldCreateAccount() {
        AccountCreationDto accountCreationDto = new AccountCreationDto();
        accountCreationDto.setName("my account");

        Account account = new Account();
        account.setName("my account");
        when(accountMapper.mapAccountCreationDtoToEntity(accountCreationDto)).thenReturn(account);

        Account savedAccount = new Account();
        savedAccount.setName("my account");
        savedAccount.setId(3L);

        when(accountRepository.save(account)).thenReturn(savedAccount);

        AccountIdDto accountIdDto = new AccountIdDto();
        accountIdDto.setId(3L);

        when(accountMapper.mapEntityToAccountIdDto(savedAccount)).thenReturn(accountIdDto);

        AccountIdDto actualAccountIdDto = accountService.createAccount(accountCreationDto);

        assertThat(actualAccountIdDto).isSameAs(accountIdDto);
    }

}