package com.gobliip.retrofit.cloud.oauth2.jwt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import retrofit.RequestInterceptor;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by lsamayoa on 14/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class OAuthJWTTokenInterceptorTest {

    private static final String MOCK_JWT_TOKEN = "This is a mock jwt token, contents do not matter";

    @Mock
    private JWTTokenStore jwtTokenStore;

    @Mock
    private RequestInterceptor.RequestFacade request;

    private OAuthJWTTokenInterceptor tokenInterceptor;

    @Before
    public void setTokenInterceptor(){
        tokenInterceptor = new OAuthJWTTokenInterceptor(jwtTokenStore);
    }

    @Test
    public void test_intercept(){
        when(jwtTokenStore.getToken()).thenReturn(Optional.of(MOCK_JWT_TOKEN));
        tokenInterceptor.intercept(request);
        verify(request).addHeader("Authorization", "Bearer " + MOCK_JWT_TOKEN);
    }

    @Test
    public void test_intercept_noToken(){
        when(jwtTokenStore.getToken()).thenReturn(Optional.<String>empty());
        tokenInterceptor.intercept(request);
        verify(request, never()).addHeader(any(), any());
    }


}
