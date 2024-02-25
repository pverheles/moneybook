package com.moneybook.controller;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Parameter(in = ParameterIn.HEADER,name = "email", required = true, content = @Content(schema = @Schema(type = "string")))
    public AccountIdDto createAccount(@RequestBody @Valid AccountCreationDto accountCreationDto) {
        return accountService.createAccount(accountCreationDto);
    }

    @GetMapping
    @Parameter(in = ParameterIn.HEADER,name = "email", required = true, content = @Content(schema = @Schema(type = "string")))
    public List<AccountReadDto> getAccounts() {
        return accountService.getAccounts();
    }
}
