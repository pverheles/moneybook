package com.moneybook.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String OAUTH_SCHEME = "auth";

    @Value("${okta.oauth2.issuer}")
    String authURL;

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME))
                .components(new Components().addSecuritySchemes(OAUTH_SCHEME, createOAuthScheme()));
    }

    private SecurityScheme createOAuthScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.OAUTH2).flows(createOAuthFlows());
    }

    private OAuthFlows createOAuthFlows() {
        final var oauthFlow = new OAuthFlow()
                .authorizationUrl(authURL + "v1/authorize")
                .refreshUrl(authURL + "v1/token")
                .tokenUrl(authURL + "v1/token")
                .scopes(new Scopes());
        return new OAuthFlows().authorizationCode(oauthFlow);
    }
}
