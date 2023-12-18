package com.moneybook.service;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.entity.Account;
import com.moneybook.mapper.AccountMapper;
import com.moneybook.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountIdDto createAccount(AccountCreationDto accountDto) {
        Account account = accountMapper.mapAccountCreationDtoToEntity(accountDto);
        account = accountRepository.save(account);
        AccountIdDto accountIdDto = accountMapper.mapEntityToAccountIdDto(account);
        return accountIdDto;
    }
}
