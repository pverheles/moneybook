package com.moneybook.repository;

import com.moneybook.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/UserRepositoryTest.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmail_shouldFindUserByEmail() {
        User user = userRepository.findByEmail("petro@mail.com").get();

        assertThat(user.getEmail()).isEqualTo("petro@mail.com");
        assertThat(user.getName()).isEqualTo("Petro Verheles");

    }

}
