package com.j0rsa.cardsbuddy;

import com.j0rsa.cardsbuddy.security.SecurityService;

import java.util.HashMap;
import java.util.Map;

public class TestSecurityService implements SecurityService {

    @Override
    public Map<String, String> headers() {
        Map<String, String> map = new HashMap<>();
        map.put(formatWithPrefix("jwt_token"), DefaultData.testTinyCardsJwt());
        map.put(formatWithPrefix("session"), DefaultData.testTinyCardsSession());
        return map;
    }

    @Override
    public String userId() {
        return DefaultData.defaultTinyCardsId();
    }

    private String formatWithPrefix(String session) {
        return String.format("%s%s", SystemConstants.TINY_CARDS_PREFIX, session);
    }
}
