package com.revature.airbnb.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String e) {
        super(e);   
    }
}