package com.revature.airbnb.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "bookingId")
    private int bookingId;

    private int renterId;
    private int listingId;
    private String startDate;
    private String endDate;
    private String status; // TODO: make enum

    public Booking(String startDate, String endDate, String status, int renterId, int listingId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.renterId = renterId;
        this.listingId = listingId;
    }

    public Booking(String startDate, String endDate, String status, int renterId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.renterId = renterId;
    }
}
