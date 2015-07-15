package com.gobliip.retrofit.cloud.oauth2.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RequestInterceptor;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * Created by lsamayoa on 7/14/15.
 */
public class OAuthJWTTokenInterceptor implements RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthJWTTokenInterceptor.class);

    private JWTTokenStore tokenStore;

    private static final MessageFormat HEADER_CONTENT_MESSAGE_FORMAT = new MessageFormat("Bearer {0}");
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    public OAuthJWTTokenInterceptor(JWTTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void intercept(RequestFacade request) {
        final Optional<String> token = tokenStore.getToken();
        if(!token.isPresent()) {
            LOGGER.warn("JWT Token is empty, skipping OAuthJWTTokenInterceptor");
            return;
        }
        request.addHeader(AUTHORIZATION_HEADER_NAME, HEADER_CONTENT_MESSAGE_FORMAT.format(new Object[]{token.get()}));
    }
}
