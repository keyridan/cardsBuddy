package com.j0rsa.cardsbuddy.security;

import com.j0rsa.cardsbuddy.security.exception.WrongCodeException;
import com.j0rsa.cardsbuddy.integration.tinycards.TinyCardsService;
import com.j0rsa.cardsbuddy.integration.tinycards.model.LoginRequest;
import com.j0rsa.cardsbuddy.integration.tinycards.model.TinyCardsLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TinyCardsAuthenticationProvider implements AuthenticationProvider {
    private final TinyCardsService tinyCardsService;

    @Autowired
    public TinyCardsAuthenticationProvider(TinyCardsService tinyCardsService) {
        this.tinyCardsService = tinyCardsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return requestNewUserToken(authentication)
                .map(this::tinyAuthenticationToken)
                .orElseThrow(WrongCodeException::new);
    }

    private Optional<TinyCardsLoginResponse> requestNewUserToken(Authentication authentication) {
        return tinyCardsService.login(loginRequestFrom(authentication));
    }

    private LoginRequest loginRequestFrom(Authentication authentication) {
        return LoginRequest.builder()
                .identifier(authentication.getPrincipal().toString())
                .password(authentication.getCredentials().toString())
                .build();
    }

    private TinyAuthenticationToken tinyAuthenticationToken(TinyCardsLoginResponse response) {
        return new TinyAuthenticationToken(response.getId(), response.getCookies());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
