package com.moneybook.controller;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountIdDto createAccount(AccountCreationDto accountCreationDto) {
        return accountService.createAccount(accountCreationDto);
    }
}
