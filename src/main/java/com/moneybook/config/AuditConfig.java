package com.moneybook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class AuditConfig {

  @Bean
  public AuditorAware<String> auditorAware(){
    return new CustomAuditAware();
  }

  @Bean
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }
}
