package com.j0rsa.cardsbuddy.security;

import lombok.Getter;
import org.assertj.core.util.Lists;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Map;

public class TinyAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 10000L;
    private final Object principal;
    @Getter
    private final Map<String, String> headers;

    public TinyAuthenticationToken(Object principal, Map<String, String> headers) {
        super(Lists.newArrayList());
        this.principal = principal;
        this.headers = headers;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
