package com.j0rsa.cardsbuddy.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SecurityService {
    Map<String, String> headers();

    String userId();

    default Authentication updateContextToken(UsernamePasswordAuthenticationToken token) {
        throw new NotImplementedException();
    }

    default void updateContextToken(String jwt, HttpServletRequest request) {
        throw new NotImplementedException();
    }
}
