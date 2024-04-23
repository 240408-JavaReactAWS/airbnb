package com.revature.airbnb.exceptions;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException(String e) {
        super(e);
    }
}