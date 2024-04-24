package com.revature.airbnb.services;

import org.springframework.stereotype.Service;

import com.revature.airbnb.daos.ListingDAO;
import com.revature.airbnb.models.Listing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ListingService {
    
    private ListingDAO listingDAO;
    
    @Autowired
    public ListingService(ListingDAO listingDAO) {
        this.listingDAO = listingDAO;
    }
    
    // Add methods for CRUD operations on Listing entity
    public List<Listing> getAllListings() {
        return listingDAO.findAll();
    }

    public Listing getListingById(int id) {
        return listingDAO.findById(id).get();
    }

    public Listing addListing(Listing listing) {
        return listingDAO.save(listing);
    }

    public Listing updateListing(Listing listing) {
        return listingDAO.save(listing);
    }
    public Listing deleteListing(int id) {
        Listing listing = listingDAO.findById(id).get();
        listingDAO.deleteById(id);
        return listing;
    }
}

