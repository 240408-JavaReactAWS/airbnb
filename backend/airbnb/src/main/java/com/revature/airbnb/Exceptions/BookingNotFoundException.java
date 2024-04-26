package com.revature.airbnb.Exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String e) {
        super(e);
    }
}