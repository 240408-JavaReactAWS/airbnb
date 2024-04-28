package com.revature.airbnb.Models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "listing_id")
    private List<Booking> bookings;

    private String address;
    private String city;
    private String state;
    private String description;
    private String[] photos;
    private String name;
    
    public Listing(String address, String city, String state, String description,
        String[] photos, String name, Owner owner) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.description = description;
        this.photos = photos;
        this.name = name;
        this.owner = owner;
    }
}
