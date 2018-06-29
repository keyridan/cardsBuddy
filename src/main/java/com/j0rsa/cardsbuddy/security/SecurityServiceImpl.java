package com.j0rsa.cardsbuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public SecurityServiceImpl(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public Map<String, String> headers() {
        return tokenProvider.getJwtFromRequest()
                .map(tokenProvider::getHeadersFromJWT)
                .orElseThrow(IllegalStateException::new);
    }
}
