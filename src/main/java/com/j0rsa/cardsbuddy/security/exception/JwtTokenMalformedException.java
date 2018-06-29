package com.j0rsa.cardsbuddy.security.exception;

/**
 * @author red
 * @since 06.06.18
 */

public class JwtTokenMalformedException extends RuntimeException {
    public JwtTokenMalformedException(String jwt_token_is_not_valid) {
    }
}
