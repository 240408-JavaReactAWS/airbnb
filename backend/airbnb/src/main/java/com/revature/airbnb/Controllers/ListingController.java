package com.revature.airbnb.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.airbnb.Exceptions.*;
import com.revature.airbnb.Models.*;
import com.revature.airbnb.Services.*;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
@RestController
@RequestMapping("listings")
public class ListingController {

    private final ListingService ls;

    @Autowired
    public ListingController(ListingService ls, OwnerService os) {
        this.ls = ls;
    }

    /* GET /listings */
    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        return new ResponseEntity<>(ls.getAllListings(), OK);
    }

    /* POST /listings */
    @PostMapping
    public ResponseEntity<Listing> createListing(@RequestHeader(name = "owner", required = true) String username, @RequestBody Listing listing, HttpSession session)  {
        Owner owner = (Owner) session.getAttribute("owner");
        if (owner == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        Listing newListing;
        try {
            newListing = ls.createListing(listing);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        } catch (InvalidAuthenticationException e) {
            return new ResponseEntity<>(FORBIDDEN);
        }
        return new ResponseEntity<>(newListing, CREATED);
    }
  
    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String invalidAuthenticationHandler(InvalidAuthenticationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ListingNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public @ResponseBody String listingNotFoundHandler(ListingNotFoundException e) {
        return e.getMessage();
    }
}
