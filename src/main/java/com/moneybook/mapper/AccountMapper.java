package com.moneybook.mapper;

import com.moneybook.dto.AccountDto;
import com.moneybook.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account mapDtoToEntity(AccountDto dto) {
        Account account = new Account();
        account.setAmount(dto.getAmount());
        account.setName(dto.getName());
        account.setBank(dto.getBank());
        account.setCurrency(dto.getCurrency());

        return account;
    }
}
