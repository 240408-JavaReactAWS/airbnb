package com.revature.airbnb.Exceptions;

public class InvalidAuthenticationException extends Exception {
    public InvalidAuthenticationException(String msg) {
        super(msg);
    }
}