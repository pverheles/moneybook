package com.moneybook.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class CustomAuditAware implements AuditorAware<String> {

  @Autowired
  private HttpServletRequest httpServletRequest;

  public CustomAuditAware() {
  }

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return Optional.empty();
    }

    if (authentication.getPrincipal() instanceof User) {
      return Optional.of(((User) authentication.getPrincipal()).getUsername());
    } else if (authentication.getPrincipal() instanceof String) {

      if (httpServletRequest != null && "/public-api/payment/callback".equals(httpServletRequest.getRequestURI())) {
        return Optional.of("WFPCallbackRequest");
      }

      return Optional.of((String)authentication.getPrincipal());
    } else {
      return null;
    }

  }
}
