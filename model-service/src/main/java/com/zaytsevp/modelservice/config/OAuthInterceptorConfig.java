package com.zaytsevp.modelservice.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * @author Pavel Zaytsev
 */
public class OAuthInterceptorConfig {
    @Bean
    public RequestInterceptor oAuth2FeignRequestInterceptor(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext,
                                                            OAuth2ProtectedResourceDetails resource) {
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, resource);
    }
}
