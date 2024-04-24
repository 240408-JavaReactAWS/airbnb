package com.revature.airbnb.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "renters")
public class Renter extends User{

    public Renter() {
        super();
    }

    public Renter(String username, String password, String email, List<Booking> bookingHistory ) {
        super(username, password, email, bookingHistory);
    }
}
