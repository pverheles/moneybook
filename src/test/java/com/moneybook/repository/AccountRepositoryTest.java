package com.moneybook.repository;

import com.moneybook.constants.Bank;
import com.moneybook.constants.Currency;
import com.moneybook.constants.State;
import com.moneybook.entity.Account;
import com.moneybook.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/AccountRepositoryTest.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void saveAccount_shouldSetCreateDateAndModifyDate() {
        User user = userRepository.findById(1L).get();

        Account account = new Account();
        account.setName("Yellow Card");
        account.setAmount(BigDecimal.ZERO);
        account.setCurrency(Currency.USD);
        account.setBank(Bank.PRIVATBANK);
        account.setState(State.A);
        account.setUser(user);

        account = accountRepository.save(account);

        assertThat(account.getCreateDate()).isToday();
        assertThat(account.getModifyDate()).isToday();
    }

    @Test
    void findByUserEmail_whenAccountsExist_shouldFindUserAccounts() {
        List<Account> accounts = accountRepository.findByUserEmail("pet@mail.com");
        assertThat(accounts).hasSize(1);
    }

    @Test
    void findByUserEmail_whenAccountsNotExist_shouldNotFindUserAccounts() {
        List<Account> accounts = accountRepository.findByUserEmail("hasnoaacounts@mail.com");
        assertThat(accounts).isEmpty();
    }
}
