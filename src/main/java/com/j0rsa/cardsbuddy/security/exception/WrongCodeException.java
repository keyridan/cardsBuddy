package com.j0rsa.cardsbuddy.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.j0rsa.cardsbuddy.SystemConstants.WRONG_CODE_EXCEPTION_MESSAGE;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongCodeException extends BadCredentialsException {
    public WrongCodeException() {
        super(WRONG_CODE_EXCEPTION_MESSAGE);
    }

    public WrongCodeException(String msg) {
        super(msg);
    }

    public WrongCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
