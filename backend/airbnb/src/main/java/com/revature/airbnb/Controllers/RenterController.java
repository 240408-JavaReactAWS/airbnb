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
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Renter;
import com.revature.airbnb.Services.BookingService;
import com.revature.airbnb.Services.RenterService;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/renters")
public class RenterController {

    private final RenterService renterService;
    private final BookingService bookingService;

    @Autowired
    public RenterController(RenterService renterService, BookingService bookingService) {
        this.renterService = renterService;
        this.bookingService = bookingService;
    }

    /*This function registers a Renter by adding their username, password, and email to the Renters table */
    @PostMapping("/register")
    public ResponseEntity<Renter> registerRenter(@RequestBody Renter renter) {
        Renter savedRenter;

        try {
            savedRenter = renterService.registerRenter(renter.getUsername(), renter.getPassword(), renter.getEmail());
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(savedRenter, CREATED);
    }

    /*This function logs in a Renter by adding their token to the Renters table */
    @PostMapping("/login")
    public ResponseEntity<Renter> loginHandler(@RequestBody Renter owner)
    {
        return ResponseEntity.ok(renterService.login(owner.getUsername(), owner.getPassword()));
    }

    /*This function logs out a Renter by removing their token from the Renters table */
    @PostMapping("/logout")
    public ResponseEntity<Renter> logoutHandler(@RequestBody String token)
    {
        System.out.println(token);
        return ResponseEntity.ok(renterService.logout(token));
    }

    /*This function retrieves all renters from the Renters table */
    @GetMapping
    public List<Renter> getAllRenters() {
        return renterService.getAllRenters();
    }

    /*This function retrieves a renter by their id from the Renters table */
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> viewAccountDetails(@PathVariable int id) {
        try {
            Renter renter = renterService.getRenterById(id);
            Map<String, Object> accountDetails = new LinkedHashMap<>();
            accountDetails.put("username", renter.getUsername());
            accountDetails.put("email", renter.getEmail());
            accountDetails.put("bookings", renter.getbookings());
            return new ResponseEntity<>(accountDetails, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    /*This function retrieves all of the bookings that are associated with a particular Renter */
    @GetMapping("{id}/bookings")
    public ResponseEntity<List<Booking>> viewBookings(@PathVariable int id) {
        try {
            List<Booking> bookings = bookingService.getBookingsByRenterId(id);
            return new ResponseEntity<>(bookings, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleUsernameAlreadyTaken(UsernameAlreadyTakenException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String InvalidAuthenticationHandler(InvalidAuthenticationException e)
    {
        return e.getMessage();
    }   
}