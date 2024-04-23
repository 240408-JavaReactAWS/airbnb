package com.revature.airbnb.exceptions;

//Thrown whenever a request tries to access an item not found in the database
public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String e)
    {
        super(e);
    }
}
