package com.moneybook.usercontext;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserEmailFilter implements Filter {

    private final UserContextService userContextService;

    public UserEmailFilter(UserContextService userContextService) {
        this.userContextService = userContextService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String email = httpServletRequest.getHeader("email");

        if (email == null) {
            throw new UnknownUserException("Unknown user " + email);
        }

        userContextService.putUserContext(new UserContext(email));

    }
}
