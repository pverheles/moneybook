package com.moneybook.mapper;

import com.moneybook.constants.State;
import com.moneybook.dto.AccountCreationDto;
import com.moneybook.dto.AccountIdDto;
import com.moneybook.dto.AccountReadDto;
import com.moneybook.dto.AccountUpdateDto;
import com.moneybook.entity.Account;
import com.moneybook.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account mapAccountCreationDtoToEntity(AccountCreationDto accountCreationDto, User user) {
        Account account = new Account();
        account.setAmount(accountCreationDto.getAmount());
        account.setName(accountCreationDto.getName());
        account.setBank(accountCreationDto.getBank());
        account.setCurrency(accountCreationDto.getCurrency());
        account.setUser(user);
        account.setState(State.A);

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

    public AccountReadDto mapEntityToAccountReadDto(Account account) {
        Long id = account.getId();
        AccountReadDto accountReadDto = new AccountReadDto();
        accountReadDto.setId(id);
        accountReadDto.setName(account.getName());
        accountReadDto.setBank(account.getBank());
        accountReadDto.setCurrency(account.getCurrency());
        accountReadDto.setState(account.getState());
        accountReadDto.setAmount(account.getAmount());
        return accountReadDto;
    }
}
