package com.revature.airbnb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="renters")
public class Renter extends User {

    public Renter()
    {
    }
    public int getRenterId() {
        return this.userId;
    }

    public void setRenterId(int userId) {
        this.userId = userId;
    }
    
}
