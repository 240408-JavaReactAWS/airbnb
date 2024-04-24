package com.revature.airbnb.Exceptions;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException(String e) {
        super(e);
    }
}