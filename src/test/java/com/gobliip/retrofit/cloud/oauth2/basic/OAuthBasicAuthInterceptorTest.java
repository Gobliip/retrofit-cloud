package com.gobliip.retrofit.cloud.oauth2.basic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import retrofit.RequestInterceptor;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by lsamayoa on 12/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class OAuthBasicAuthInterceptorTest {

    private static final String OAUTH_BASIC = "Basic YWNtZTphY21lc2VjcmV0";

    @Mock
    RequestInterceptor.RequestFacade requestFacade;

    @Mock
    BasicAuthCredentialsStore credentialsStore;

    private OAuthBasicAuthInterceptor authInterceptor;

    @Before
    public void setAuthInterceptor(){
        this.authInterceptor = new OAuthBasicAuthInterceptor(credentialsStore);
    }

    @Test
    public void test_intercept(){
        when(credentialsStore.getUsername()).thenReturn(Optional.of("acme"));
        when(credentialsStore.getPassword()).thenReturn(Optional.of("acmesecret"));
        authInterceptor.intercept(requestFacade);
        verify(requestFacade, times(1)).addHeader("Authorization", OAUTH_BASIC);
    }

    @Test
    public void test_intercept_missingUsername(){
        when(credentialsStore.getUsername()).thenReturn(Optional.<String>empty());
        when(credentialsStore.getPassword()).thenReturn(Optional.of("acmesecret"));
        authInterceptor.intercept(requestFacade);
        verify(requestFacade, never()).addHeader(any(), any());
    }

    @Test
    public void test_intercept_missingPassword(){
        when(credentialsStore.getUsername()).thenReturn(Optional.of("acme"));
        when(credentialsStore.getPassword()).thenReturn(Optional.<String>empty());
        authInterceptor.intercept(requestFacade);
        verify(requestFacade, never()).addHeader(any(), any());
    }

}
