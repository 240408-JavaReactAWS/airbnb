package com.revature.airbnb.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    private String startDate;
    private String endDate;
    private String status; // possibly make enum
    private int renterId;
    private int listingId;

    /* for Jackson databind */
    public Booking() {
    }

    public Booking(int bookingId, String startDate, String endDate, String status, int renterId, int listingId) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.renterId = renterId;
        this.listingId = listingId;
    }

    /* POST /renters/{id}/bookings */
    public Booking(String startDate, String endDate, String status, int renterId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.renterId = renterId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
