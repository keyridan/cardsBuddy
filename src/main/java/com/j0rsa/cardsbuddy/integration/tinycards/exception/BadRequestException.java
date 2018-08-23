package com.j0rsa.cardsbuddy.integration.tinycards.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String reason) {
        super(reason);
    }
}
