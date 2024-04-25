package com.revature.airbnb.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "owners")
public class Owner extends User{

    // add owner_id foreign key to listings table
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner_id")
    private List<Listing> listings;

    public Owner() {
      
    }

    public Owner(String username, String password, String email, List<Listing> listings) {
        super(username, password, email);

        this.listings = listings;
    }

    public List<Listing> getListings() {
        return listings;
    }
    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

}