package com.gobliip.retrofit.cloud.oauth2.jwt;

import java.util.Optional;

/**
 * Created by lsamayoa on 7/14/15.
 */
public interface JWTTokenStore {
    Optional<String> getToken();
    void setToken(String token);
}
