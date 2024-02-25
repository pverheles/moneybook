package com.moneybook.service;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.entity.Account;
import com.moneybook.entity.User;
import com.moneybook.mapper.AccountMapper;
import com.moneybook.repository.AccountRepository;
import com.moneybook.repository.UserRepository;
import com.moneybook.usercontext.UserContext;
import com.moneybook.usercontext.UserContextService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    UserContextService userContextService;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountMapper accountMapper;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(userContextService,
                userRepository, accountRepository, accountMapper);
    }

    @Test
    void createAccount_shouldCreateAccountForUserInContext() {
        UserContext userContext = new UserContext("petro@mail.com");
        User user = new User();
        user.setEmail("petro@mail.com");

        when(userContextService.getUserContext()).thenReturn(userContext);
        when(userRepository.findByEmail("petro@mail.com")).thenReturn(Optional.of(user));

        AccountCreationDto accountCreationDto = new AccountCreationDto();
        accountCreationDto.setName("my account");

        Account account = new Account();
        account.setName("my account");
        when(accountMapper.mapAccountCreationDtoToEntity(accountCreationDto, user)).thenReturn(account);

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

    @Test
    void getAccounts_shouldReturnListOfAccountsOfUserInContext() {
        UserContext userContext = new UserContext("petro@mail.com");

        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setName("test");
        accounts.add(account);

        AccountReadDto accountReadDto = new AccountReadDto();
        accountReadDto.setName("test");

        when(userContextService.getUserContext()).thenReturn(userContext);
        when(accountRepository.findByUserEmail("petro@mail.com")).thenReturn(accounts);
        when(accountMapper.mapEntityToAccountReadDto(account)).thenReturn(accountReadDto);

        List<AccountReadDto> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(accountReadDto);

        List<AccountReadDto> actualAccounts = accountService.getAccounts();

        assertThat(actualAccounts).isEqualTo(expectedAccounts);
    }

}