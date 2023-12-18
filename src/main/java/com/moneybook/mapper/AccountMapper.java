package com.moneybook.mapper;

import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountUpdateDto;
import com.moneybook.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account mapAccountCreationDtoToEntity(AccountCreationDto accountCreationDto) {
        Account account = new Account();
        account.setAmount(accountCreationDto.getAmount());
        account.setName(accountCreationDto.getName());
        account.setBank(accountCreationDto.getBank());
        account.setCurrency(accountCreationDto.getCurrency());

        return account;
    }

    public Account mapAccountUpdateDtoToEntity(AccountUpdateDto accountUpdateDto, Account account) {
        account.setName(accountUpdateDto.getName());
        return account;
    }

    public AccountIdDto mapEntityToAccountIdDto(Account account) {
        Long id = account.getId();
        AccountIdDto accountIdDto = new AccountIdDto();
        accountIdDto.setId(id);
        return accountIdDto;
    }
}
