package com.revature.airbnb.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table (name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @ManyToOne
    @JoinColumn(name="renter_id")
    private Renter renter;

    @ManyToOne
    @JoinColumn(name="listing_id")
    private Listing listing;

    private String startDate;
    private String endDate;
    private String status; // TODO: make enum

    public Booking(String startDate, String endDate, String status, Renter renter, Listing listing) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.renter = renter;
        this.listing = listing;
    }

    public Booking(String startDate, String endDate, String status, Renter renter) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.renter = renter;
    }
}
