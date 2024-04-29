package com.revature.airbnb.Exceptions;

public class InvalidRegistrationException extends RuntimeException {
    public InvalidRegistrationException(String e) {
        super(e);
    }
}
