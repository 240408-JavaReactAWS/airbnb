package com.revature.airbnb.Controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Exceptions.InvalidRegistrationException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Renter;
import com.revature.airbnb.Services.ListingService;
import com.revature.airbnb.Services.RenterService;
import static org.springframework.http.HttpStatus.*;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowCredentials = "true")
@RestController
@RequestMapping("/renters")
public class RenterController {

    private final RenterService rs;
    private final ListingService ls;

    @Autowired
    public RenterController(RenterService rs, ListingService ls) {
        this.rs = rs;
        this.ls = ls;
    }

    /* Creates new Renter */
    @PostMapping("register")
    public ResponseEntity<Renter> registerRenter(@RequestBody Renter renter, HttpSession session) {
        Renter savedRenter;
        try {
            savedRenter = rs.registerRenter(renter.getUsername(), renter.getPassword(), renter.getEmail());
            session.setAttribute("renter", savedRenter); // Store the renter in the session
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(savedRenter, CREATED);
    }

    /*This function logs in a Renter by adding their token to the Renters table */
    @PostMapping("/login")
    public ResponseEntity<Renter> loginHandler(@RequestBody Renter renter) {
        return ResponseEntity.ok(rs.login(renter.getUsername(), renter.getPassword()));
    }

    /*This function logs out a Renter by removing their token from the Renters table */
    @PostMapping("/logout")
    public ResponseEntity<Renter> logoutHandler(@RequestBody String token) {
        return ResponseEntity.ok(rs.logout(token));
    }

    /*This function retrieves all renters from the Renters table */
    @GetMapping
    public List<Renter> getAllRenters() {
        return rs.getAllRenters();
    }

    /*This function retrieves a renter by their id from the Renters table */
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> viewAccountDetails(@PathVariable int id) {
        try {
            Renter renter = rs.getRenterById(id);
            Map<String, Object> accountDetails = new LinkedHashMap<>();
            accountDetails.put("username", renter.getUsername());
            accountDetails.put("email", renter.getEmail());
            accountDetails.put("bookings", renter.getBookings());
            return new ResponseEntity<>(accountDetails, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    /* Returns all listings for which a renter sent a booking request */
    @GetMapping("{id}/listings")
    public ResponseEntity<List<Listing>> viewListings(@PathVariable int id, @RequestParam String token) {
        Renter renter = rs.getRenterByToken(token);
        List<Listing> listings = renter.getBookings().stream().map((Booking booking) -> {
            return ls.getListingById(booking.getListingId());
        }).toList();
        return new ResponseEntity<>(listings, OK);
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String handleUsernameAlreadyTaken(UsernameAlreadyTakenException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String InvalidAuthenticationHandler(InvalidAuthenticationException e) {
        return e.getMessage();
    }   
}