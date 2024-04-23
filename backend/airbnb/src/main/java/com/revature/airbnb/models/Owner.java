package com.revature.airbnb.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner extends User {
    
    //@OneToMany(mappedBy = "owner")
    private List<Listing> listings;

    public Owner()
    {
        super();
    }
 
    public List<Listing> getListings() {
        return this.listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

}
