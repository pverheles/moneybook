package com.moneybook.service;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.entity.Account;
import com.moneybook.entity.User;
import com.moneybook.mapper.AccountMapper;
import com.moneybook.repository.AccountRepository;
import com.moneybook.repository.UserRepository;
import com.moneybook.usercontext.UserContextService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final UserContextService userContextService;

    public AccountService(UserContextService userContextService, UserRepository userRepository,
                          AccountRepository accountRepository,
                          AccountMapper accountMapper) {
        this.userContextService = userContextService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountIdDto createAccount(AccountCreationDto accountDto) {
        String email = userContextService.getUserContext().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        Account account = accountMapper.mapAccountCreationDtoToEntity(accountDto, user);
        account = accountRepository.save(account);
        AccountIdDto accountIdDto = accountMapper.mapEntityToAccountIdDto(account);
        return accountIdDto;
    }

    public List<AccountReadDto> getAccounts() {
        String email = userContextService.getUserContext().getEmail();
        List<Account> accounts = accountRepository.findByUserEmail(email);
        return accounts.stream().map(accountMapper::mapEntityToAccountReadDto).toList();
    }
}
