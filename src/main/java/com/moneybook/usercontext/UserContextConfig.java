package com.moneybook.usercontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserContextConfig {

    @Bean
    ThreadLocal<UserContext> userContextThreadLocal() {
        return new ThreadLocal<>();
    }
}
