package com.j0rsa.cardsbuddy.security;

import java.util.Map;

public interface SecurityService {
    Map<String, String> headers();
    String userId();
}
