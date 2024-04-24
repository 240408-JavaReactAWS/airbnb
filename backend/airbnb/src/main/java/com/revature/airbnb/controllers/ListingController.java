package com.revature.airbnb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.airbnb.models.Listing;
import com.revature.airbnb.services.ListingService;

@RestController
@RequestMapping("/listings")
@ResponseBody
public class ListingController {
        
    @Autowired
    private ListingService listingService;
        // Add methods for CRUD operations on Listing entity

    @GetMapping()
    public ResponseEntity<List<Listing>> getAllListings()
    {
        return new ResponseEntity<List<Listing>>(listingService.getAllListings(), HttpStatus.OK);
    }
    
}
