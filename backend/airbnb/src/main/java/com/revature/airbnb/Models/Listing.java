package com.revature.airbnb.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private int listingId;

    private String address;
    private String city;
    private String state;
    private String description;
    private ArrayList<String> photos;
    private String name;

    // add listing_id foreign key in bookings table
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_listing_id")
    private List<Booking> bookings;
    
    public Listing(int listingId, String address, String city, String state, String description,
            ArrayList<String> photos, String name) {
        this.listingId = listingId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.description = description;
        this.photos = photos;
        this.name = name;
    }
    public int getListingId() {
        return listingId;
    }
    public void setListingId(int listingId) {
        this.listingId = listingId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<String> getPhotos() {
        return photos;
    }
    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
