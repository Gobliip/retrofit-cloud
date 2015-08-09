package com.gobliip.retrofit.cloud.oauth2.jwt;

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
    public synchronized void setToken(String token) {
        this.token = token;
    }

}
