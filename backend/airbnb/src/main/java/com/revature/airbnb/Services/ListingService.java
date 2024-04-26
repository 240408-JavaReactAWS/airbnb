package com.revature.airbnb.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.airbnb.DAOs.ListingDAO;
import com.revature.airbnb.DAOs.OwnerDAO;
import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Owner;

@Service
public class ListingService {
    private final ListingDAO listingDAO;
    private final OwnerDAO ownerDAO;

    public ListingService(ListingDAO listingDAO, OwnerDAO ownerDAO) {
        this.listingDAO = listingDAO;
        this.ownerDAO = ownerDAO;
    }

    public List<Listing> getAllListings() {
        return listingDAO.findAll();
    }

    /* This method is used to get all listings by a specific owner */
    public List<Listing> getListingsByOwner(Owner owner) {
        return listingDAO.findByOwner(owner);
    } 

    public Listing getListingById(int id) {
        return listingDAO.findById(id).orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    }

    public Listing createListing(Listing listing) throws InvalidAuthenticationException{
        return listingDAO.save(listing);
    }
}
