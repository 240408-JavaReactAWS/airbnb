package com.revature.airbnb.Controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.revature.airbnb.Exceptions.BookingNotFoundException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Owner;
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
    /* TODO: Controllers should handle exceptions, not throw them */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @RequestParam String token) {
        Renter renter = renterService.getRenterByToken(token);
        booking.setRenterId(renter.getUserId());
        return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
    }

    /* As an owner, update a booking's status */
    @PatchMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@RequestBody String status, @RequestParam String token, @PathVariable int bookingId) {
        return new ResponseEntity<>
            (bookingService.updateBookingStatus(bookingService.findById(bookingId), status), HttpStatus.OK);
    }
 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }
 
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String BookingNotFoundHandler(BookingNotFoundException e) {
        return e.getMessage();
    }   
}