package com.j0rsa.cardsbuddy;

import com.j0rsa.cardsbuddy.security.SecurityService;

import java.util.HashMap;
import java.util.Map;

public class TestSecurityService implements SecurityService {
    Map<String, String> headers = new HashMap<>();

    {
        headers.put(formatWithPrefix("jwt_token"), DefaultData.testTinyCardsJwt());
        headers.put(formatWithPrefix("session"), DefaultData.testTinyCardsSession());
    }

    @Override
    public Map<String, String> headers() {
        return headers;
    }

    @Override
    public String userId() {
        return DefaultData.defaultTinyCardsId();
    }

    private String formatWithPrefix(String session) {
        return String.format("%s%s", SystemConstants.TINY_CARDS_PREFIX, session);
    }
}
