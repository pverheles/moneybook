package com.moneybook.repository;

import com.moneybook.entity.Account;
import com.moneybook.entity.Operation;
import com.moneybook.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/OperationRepositoryTest.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class OperationRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Test
    void findAllByUserAndOperationDateBetween() {
        User user = userRepository.findById(1L).get();

        Account account = accountRepository.findById(1L).get();

        Page<Operation> page = operationRepository.findAllByUserAndAccountAndOperationDateTimeBetween(user, account,
                LocalDateTime.of(2023, Month.OCTOBER, 1, 0, 0),
                LocalDateTime.of(2023, Month.OCTOBER, 30, 0, 0),
                PageRequest.of(0, 10));

        assertThat(page).hasSize(1);
    }

}