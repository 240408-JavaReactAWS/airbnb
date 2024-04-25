package com.revature.airbnb.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private int listingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner_id")
    @JsonIgnore
    private Owner owner;

    private String address;
    private String city;
    private String state;
    private String description;
    private String[] photos;
    private String name;
    // @JsonIgnore // TODO: validate we don't need owner obj
    // private Owner owner;
    private int ownerId;

    // add listing_id foreign key in bookings table
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_listing_id")
    private List<Booking> bookings;

    public Listing() {}
    
    public Listing(int listingId, String address, String city, String state, String description,
        String[] photos, String name, int ownerId) {
        this.listingId = listingId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.description = description;
        this.photos = photos;
        this.name = name;
        this.ownerId = ownerId;
    }

    public Listing(String address, String city, String state, String description,
            String[] photos, String name, int ownerId) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.description = description;
        this.photos = photos;
        this.name = name;
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
    public String[] getPhotos() {
        return photos;
    }
    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

}
