package com.moneybook.usercontext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserContextServiceTest {

    UserContextService userContextService;

    @Mock
    ThreadLocal<UserContext> userContextThreadLocal;

    @BeforeEach
    void setUp() {
        userContextService = new UserContextService(userContextThreadLocal);
    }

    @Test
    void putUserContext() {
        UserContext userContext = new UserContext("my@mail.com");

        userContextService.putUserContext(userContext);

        verify(userContextThreadLocal).set(userContext);
    }

    @Test
    void getUserContext() {
        UserContext userContext = new UserContext("my@mail.com");

        when(userContextThreadLocal.get()).thenReturn(userContext);

        UserContext expectedUserContext = userContextService.getUserContext();

        assertThat(userContext).isSameAs(expectedUserContext);
    }
}