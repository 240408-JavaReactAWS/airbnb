package com.revature.airbnb.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.airbnb.Exceptions.*;
import com.revature.airbnb.Models.*;
import com.revature.airbnb.Services.*;
import static org.springframework.http.HttpStatus.*;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
@RestController
@RequestMapping("renters")
public class RenterController {

    private final RenterService rs;
    private final ListingService ls;

    @Autowired
    public RenterController(RenterService rs, ListingService ls) {
        this.rs = rs;
        this.ls = ls;
    }

    /* creates a new renter */
    /* POST /renters/register */
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

    /* Login existing Renter using HttpSession */
    /* POST /renters/login */
    @PostMapping("login")
    public ResponseEntity<Renter> loginHandler(@RequestBody Renter renter, HttpSession session) {
        Renter loggedInRenter;
        try {
            loggedInRenter = rs.login(renter.getUsername(), renter.getPassword());
            session.setAttribute("renter", loggedInRenter); // Store the renter in the session
        } catch (InvalidAuthenticationException | UserNotFoundException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(loggedInRenter, OK);
    }

    /* Logout existing Renter using HttpSession */
    /* POST /renters/logout */
    @PostMapping("logout")
    public ResponseEntity<Void> logoutHandler(HttpSession session) {
        session.removeAttribute("renter"); // Remove the renter from the session
        session.invalidate();
        return new ResponseEntity<>(OK);
    }

    @GetMapping("session")
    public ResponseEntity<Renter> validateSession(HttpSession session) {
        Renter renter = (Renter) session.getAttribute("renter");
        if (renter == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        return new ResponseEntity<>(renter, OK);
    }

    /* GET /renters */
    @GetMapping
    public ResponseEntity<List<Renter>> getAllRenters() {
        return new ResponseEntity<>(rs.getAllRenters(), OK);
    }

    /* GET /renters/id */
    @GetMapping("{id}")
    public ResponseEntity<Renter> viewAccountDetails(@RequestHeader(name = "renter", required = true) String username, HttpSession session, @PathVariable int id) {
        Renter renter = (Renter) session.getAttribute("renter");
        if (renter == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        Renter foundRenter;
        try {
            foundRenter = rs.getRenterByUsernameAndId(renter.getUsername(), id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(FORBIDDEN);
        } catch (InvalidAuthenticationException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(foundRenter, OK);
    }

    /* GET /renters/id/listings -> returns all listings which a renter sent a booking request */
    @GetMapping("{id}/listings")
    public ResponseEntity<List<Listing>> viewListings(@PathVariable int id, HttpSession session) {
        Renter renter = (Renter) session.getAttribute("renter");
        if (renter == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        Renter foundRenter;
        try {
            foundRenter = rs.getRenterByUsernameAndId(renter.getUsername(), id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(FORBIDDEN);
        } catch (InvalidAuthenticationException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        List<Listing> listings = foundRenter.getBookings().stream().map((Booking booking) -> {
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