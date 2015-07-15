package com.gobliip.retrofit.cloud.oauth2.jwt;

import org.apache.commons.lang3.Validate;

import java.util.Optional;

/**
 * Created by lsamayoa on 7/14/15.
 */
public class InMemoryJWTTokenStore implements JWTTokenStore {

    private String token;

    public InMemoryJWTTokenStore() {
    }

    public InMemoryJWTTokenStore(String token) {
        this.token = token;
    }

    @Override
    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }

    @Override
    public void setToken(String token) {
        Validate.notNull(token, "token must not be null");
        synchronized (this) {
            this.token = token;
        }
    }

}
