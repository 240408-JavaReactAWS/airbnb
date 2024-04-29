package com.revature.airbnb.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.airbnb.DAOs.ListingDAO;
import com.revature.airbnb.DAOs.OwnerDAO;
import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Models.Listing;

@Service
public class ListingService {
    private final ListingDAO listingDAO;

    public ListingService(ListingDAO listingDAO, OwnerDAO ownerDAO) {
        this.listingDAO = listingDAO;
    }

    public Listing createListing(Listing listing) throws InvalidAuthenticationException {
        return listingDAO.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingDAO.findAll();
    }

    public Listing getListingById(int id) {
        return listingDAO.findById(id).orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    }
}
