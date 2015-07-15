package com.gobliip.retrofit.cloud.oauth2.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RequestInterceptor;

import java.text.MessageFormat;
import java.util.Base64;
import java.util.Optional;

/**
 * Created by lsamayoa on 12/07/15.
 */
public class OAuthBasicAuthInterceptor implements RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthBasicAuthInterceptor.class);

    private static final MessageFormat AUTH_STRING_MESSAGE_FORMAT = new MessageFormat("{0}:{1}");
    private static final MessageFormat AUTH_HEADER_MESSAGE_FORMAT = new MessageFormat("Basic {0}");

    private BasicAuthCredentialsStore credentialsStore;

    public OAuthBasicAuthInterceptor(BasicAuthCredentialsStore credentialsStore) {
        this.credentialsStore = credentialsStore;
    }

    public void intercept(RequestFacade request) {
        final Optional<String> username = credentialsStore.getUsername();
        final Optional<String> password = credentialsStore.getPassword();

        if(!username.isPresent()){
            LOGGER.warn("Username not present in BasicAuthCredentialsStore, skipping OAuthBasicAuthInterceptor");
            return;
        }

        if(!password.isPresent()){
            LOGGER.warn("Password not present in BasicAuthCredentialsStore, skipping OAuthBasicAuthInterceptor");
            return;
        }

        final String authString = AUTH_STRING_MESSAGE_FORMAT.format(new Object[]{username.get(), password.get()});
        request.addHeader("Authorization", AUTH_HEADER_MESSAGE_FORMAT.format(new Object[]{Base64.getEncoder().encodeToString(authString.getBytes())}));
    }
}
