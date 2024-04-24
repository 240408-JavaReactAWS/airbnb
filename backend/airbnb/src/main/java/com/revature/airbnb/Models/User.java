package com.revature.airbnb.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;
    private String email;

    // add renter_id foreign key in bookings table
    // in users class bc both renters and owners can be renters
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_renter_id")
    private List<Booking> bookingHistory;

    public User() {}

    public User(String username, String password, String email, List<Booking> bookingHistory) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bookingHistory = bookingHistory;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }
    public void setBookingHistory(List<Booking> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }


}
