package com.revature.airbnb.Exceptions;

public class ListingNotFoundException extends RuntimeException {
    public ListingNotFoundException(String e) {
        super(e);
    }
}
