package com.revature.airbnb.Models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_renter_id")
    private Renter renter;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_listing_id")
    private Listing listing;

    private String startDate; //Format: yyyy-mm-dd from HTML date input
    private String endDate;
    private String status; // possibly make enum

    //No-args constructor for Jackson databind
    public Booking() {
        
    }

    public Booking(int bookingId, Renter renter, Listing listing, String startDate, String endDate, String status) {
        this.bookingId = bookingId;
        this.renter = renter;
        this.listing = listing;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Booking(Renter renter, Listing listing, String startDate, String endDate, String status) {
        this.renter = renter;
        this.listing = listing;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }


    public int getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Renter getRenter() {
        return this.renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Listing getListing() {
        return this.listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}    