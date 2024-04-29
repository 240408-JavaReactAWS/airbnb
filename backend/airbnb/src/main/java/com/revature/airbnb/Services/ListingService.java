package com.revature.airbnb.Services;

import java.util.List;
import java.util.Optional;
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

    public Listing createListing(Listing listing) throws InvalidAuthenticationException {
        return listingDAO.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingDAO.findAll();
    }

    public Listing getListingById(int id) {
        return listingDAO.findById(id).orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    }

    // public List<Listing> getListingsByOwner(Owner owner) {
    //     Optional<Owner> foundOwner = ownerDAO.findById(owner.getUserId());
    //     if (foundOwner.isPresent()) {
    //         return foundOwner.get().getListings();
    //     } else {
    //         throw new InvalidAuthenticationException("Owner not found with id: " + owner.getUserId());
    //     }
    // } 
}
