package com.revature.airbnb.models;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;
    private List<Listing> listings;

    public int getId() {
        return this.userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    //No-args constructor for Jackston databind
    public User(){ }

    public User(int id, String username, String password)
    {
        this.userId = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Listing> getListings() {
        return this.listings;
    }

    public void setItems(List<Listing> listings) {
        this.listings = listings;
    }

}