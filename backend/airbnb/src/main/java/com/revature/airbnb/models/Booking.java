package com.revature.airbnb.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //It should be auto-generated
    private int bookingId;

    //@ManyToOne(fetch=FetchType.EAGER)
    //@JoinColumn(name = "fk_user_id")
    private Renter renter;
    private int propertyId;
    private Date startDate;
    private Date endDate;
    private String status;

    //No-args constructor for Jackson databind
    public Booking()
    {

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

    public int getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
