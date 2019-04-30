package com.zaytsevp.modelservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created on 15.11.2018.
 *
 * @author Korovin Anatoliy
 */
@EnableOAuth2Client
@Configuration
public class FeignConfig {

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();

        resource.setAccessTokenUri("http://127.0.0.1:8055/auth/login");
        resource.setUserAuthorizationUri("http://127.0.0.1:8055/auth/authorize");
        resource.setClientId("my-client");
        resource.setClientSecret("my-secret");

        return resource;
    }

}
