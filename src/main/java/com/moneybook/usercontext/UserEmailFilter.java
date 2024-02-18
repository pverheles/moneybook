package com.moneybook.usercontext;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class UserEmailFilter implements Filter {

    private static final String API_PREFIX = "/api/";

    private final UserContextService userContextService;

    public UserEmailFilter(UserContextService userContextService) {
        this.userContextService = userContextService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        if (isApiCall(httpServletRequest)) {
            handleEmailHeader(httpServletRequest);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isApiCall(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getServletPath().startsWith(API_PREFIX);
    }

    private void handleEmailHeader(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getHeader("email");

        if (email == null) {
            throw new UnknownUserException("Unknown user");
        }

        userContextService.putUserContext(new UserContext(email));
    }
}
