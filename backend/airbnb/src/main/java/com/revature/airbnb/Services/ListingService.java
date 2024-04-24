package com.revature.airbnb.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.airbnb.DAOs.ListingDAO;
import com.revature.airbnb.Models.Listing;

@Service
public class ListingService {
    private final ListingDAO listingDAO;

    public ListingService(ListingDAO listingDAO) {
        this.listingDAO = listingDAO;
    }

    public List<Listing> getAllListings() {

        return listingDAO.findAll();
    }
}
