package com.revature.airbnb.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "renters")
public class Renter extends User{

    public Renter() {
        super();
    }

    // add renter_id foreign key in bookings table
    // in users class bc both renters and owners can be renters
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_renter_id")
    private List<Booking> bookings;

    public Renter(String username, String password, String email, List<Booking> bookings) {
        super(username, password, email);
        this.bookings = bookings;
    }

    public List<Booking> getbookings() {
        return bookings;
    }

    public void setbookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

}
