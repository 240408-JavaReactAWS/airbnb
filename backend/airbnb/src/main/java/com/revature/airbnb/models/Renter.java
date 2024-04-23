package com.revature.airbnb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="renter")
public class Renter extends User {
    
    public Renter()
    {
        super();
    }

    
}
