package com.revature.airbnb.Controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Renter;
import com.revature.airbnb.Services.BookingService;
import com.revature.airbnb.Services.RenterService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RenterService renterService;
    @Autowired
    public BookingController(BookingService bookingService, RenterService renterService) {
        this.bookingService = bookingService;
        this.renterService = renterService;
    }

    /*This function retrieves all bookings from the Bookings table */
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /*This function adds an entry in the Bookings table, using a token from a User to determine its creator */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @RequestParam String token) {
        try {
            Renter loggedInRenter = renterService.getRenterByToken(token);
            if (loggedInRenter != null) {
                booking.setRenterId(loggedInRenter.getUserId());
                Booking newBooking = bookingService.createBooking(booking);
                return new ResponseEntity<>(newBooking, CREATED);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return null;
    }
}