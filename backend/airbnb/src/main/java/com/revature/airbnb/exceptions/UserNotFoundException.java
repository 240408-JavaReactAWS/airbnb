package com.revature.airbnb.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String e)
    {
        super(e);
    }
}
