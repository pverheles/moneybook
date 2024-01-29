package com.moneybook.usercontext;

import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    private final ThreadLocal<UserContext> userContextThreadLocal;

    public UserContextService(ThreadLocal<UserContext> userContextThreadLocal) {
        this.userContextThreadLocal = userContextThreadLocal;
    }

    public void putUserContext(UserContext userContext) {
        this.userContextThreadLocal.set(userContext);
    }

    public UserContext getUserContext() {
        return userContextThreadLocal.get();
    }
}
