package com.gobliip.retrofit.cloud.oauth2.basic;

import java.util.Optional;

/**
 * Created by lsamayoa on 14/07/15.
 */
public class InMemoryBasicAuthCredentialsStore implements BasicAuthCredentialsStore {

    private String username;
    private String password;

    public InMemoryBasicAuthCredentialsStore() {
    }

    public InMemoryBasicAuthCredentialsStore(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

    @Override
    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    @Override
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
