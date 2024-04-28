package com.revature.airbnb.Models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "renters")
public class Renter extends User {
    public Renter() {
        super();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "renter_id")
    private List<Booking> bookings;

    public Renter(String username, String password, String email, List<Booking> bookings) {
        super(username, password, email);
        this.bookings = bookings;
    }
}
