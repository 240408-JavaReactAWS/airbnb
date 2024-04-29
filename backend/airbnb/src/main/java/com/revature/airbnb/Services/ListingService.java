package com.revature.airbnb.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.airbnb.DAOs.ListingDAO;
import com.revature.airbnb.DAOs.OwnerDAO;
import com.revature.airbnb.Exceptions.ListingNotFoundException;
import com.revature.airbnb.Models.Listing;

@Service
public class ListingService {
    private final ListingDAO listingDAO;

    public ListingService(ListingDAO listingDAO, OwnerDAO ownerDAO) {
        this.listingDAO = listingDAO;
    }

    public Listing createListing(Listing listing) {
        return listingDAO.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingDAO.findAll();
    }

    public Listing getListingById(int id) throws ListingNotFoundException{
        return listingDAO.findById(id).orElseThrow(() -> new ListingNotFoundException("Listing not found with id: " + id));
    }
}
