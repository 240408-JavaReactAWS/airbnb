package com.revature.airbnb.Controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Exceptions.InvalidRegistrationException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Services.BookingService;
import com.revature.airbnb.Services.ListingService;
import com.revature.airbnb.Services.OwnerService;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final ListingService listingService;
    private final BookingService bookingService;

    @Autowired
    public OwnerController(OwnerService ownerService, ListingService listingService, BookingService bookingService) {
        this.ownerService = ownerService;
        this.listingService = listingService;
        this.bookingService = bookingService;
    }

    /*This function registers an Owner by adding their username, password, and email to the Owners table */
    @PostMapping("/register")
    public ResponseEntity<Owner> registerOwner(@RequestBody Owner owner) {
        Owner savedOwner;
        try {
            savedOwner = ownerService.registerOwner(owner.getUsername(), owner.getPassword(), owner.getEmail());
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST); // TODO: returning 500 internal error when supposed to return 400 bad request
        }
        return new ResponseEntity<>(savedOwner, CREATED);
    }

    /*This function logs in an Owner by adding their token to the Owners table */
    // @PostMapping("/login")
    // public ResponseEntity<Owner> loginHandler(@RequestBody Owner owner) {
    //     return ResponseEntity.ok(ownerService.login(owner.getUsername(), owner.getPassword()));
    // }

    /*This function logs out an Owner by removing their token from the Owners table */
    // @PostMapping("/logout")
    // public ResponseEntity<Owner> logoutHandler(@RequestBody String token) {
    //     return ResponseEntity.ok(ownerService.logout(token));
    // }

    /*This function retrieves all Owners from the Owners table */
    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> viewAccountDetails(@PathVariable int id) {
        try {
            Owner owner = ownerService.getOwnerById(id);
            Map<String, Object> accountDetails = new LinkedHashMap<>();
            accountDetails.put("username", owner.getUsername());
            accountDetails.put("email", owner.getEmail());
            accountDetails.put("listings", owner.getListings());
            return new ResponseEntity<>(accountDetails, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }
  
    /* This function retrieves all listings for a particular Owner, using the Owner's ID */
    @GetMapping("{id}/listings")
    public List<Listing> getAllOwners(@PathVariable int id) {
        return ownerService.getOwnerListings(id);
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleUsernameAlreadyTaken(UsernameAlreadyTakenException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String InvalidAuthenticationHandler(InvalidAuthenticationException e) {
        return e.getMessage();
    }
}
