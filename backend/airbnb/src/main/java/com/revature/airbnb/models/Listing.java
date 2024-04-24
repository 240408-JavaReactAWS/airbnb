package com.revature.airbnb.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "listings")

public class Listing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listingId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private Owner owner;

    private String address;
    private String city;
    private String state;
    private String description;
    private List<String> photos;

    //No-args constructor for Jackson databind
    public Listing() {
    }

    public int getListingId() {
        return this.listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }


}
