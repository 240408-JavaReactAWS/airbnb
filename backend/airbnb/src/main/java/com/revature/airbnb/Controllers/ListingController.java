package com.revature.airbnb.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Services.ListingService;
import com.revature.airbnb.Services.OwnerService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;
    private final OwnerService ownerService;

    @Autowired
    public ListingController(ListingService listingService, OwnerService ownerService) {
        this.listingService = listingService;
        this.ownerService = ownerService;
    }

    /* This function retrieves all listings from the Listings table */
    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    /* This function adds an entry in the Listings table, using a token from an Owner to determine its creator */
    // @PostMapping
    // public ResponseEntity<Listing> createListing(@RequestBody Listing listing, @RequestParam String token)  {
    //     Owner owner = ownerService.getOwnerByToken(token);
    //     listing.setOwnerId(owner.getUserId());
    //     return new ResponseEntity<>(listingService.createListing(listing), HttpStatus.CREATED);
    // }
  
    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String InvalidAuthenticationHandler(InvalidAuthenticationException e) {
        return e.getMessage();
    }
}
