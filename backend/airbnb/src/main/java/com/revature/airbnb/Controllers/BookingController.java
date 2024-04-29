package com.revature.airbnb.Controllers;

import static org.springframework.http.HttpStatus.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.airbnb.Exceptions.*;
import com.revature.airbnb.Models.*;
import com.revature.airbnb.Services.*;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH}, allowCredentials = "true")
@RestController
@RequestMapping("bookings")
public class BookingController {

    private final BookingService bs;

    @Autowired
    public BookingController(BookingService bs) {
        this.bs = bs;
    }

    /* GET /bookings */
    //QA: Should this endpoint be available for renters and owners?
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bs.getAllBookings(), OK);
    }

    /* As an renter, create a Booking request for a specific listing */
    /* POST /bookings */

    /*
     * This method will allow a renter to create a booking.
     * The renter must be logged in to create a booking. If they are not, they will receive a 401 Unauthorized response.
     * If the renter is logged in, the booking will be created and returned with a 201 Created response.
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, HttpSession session)  {
        Renter renter = (Renter) session.getAttribute("renter");
        if (renter == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        Booking newBooking;
        newBooking = bs.createBooking(booking);
        return new ResponseEntity<>(newBooking, CREATED);
    }

    /* As an owner, update a booking's status */
    /* PATCH /bookings/id */
    // TODO: Implement this method
    // @PatchMapping("/{bookingId}")
    // public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, HttpSession session, @PathVariable int bookingId) {
    //     Owner owner = (Owner) session.getAttribute("owner");
    //     if (owner == null) {
    //         return new ResponseEntity<>(UNAUTHORIZED);
    //     }
    //     Booking updatedBooking;
    //     try {
    //         Booking foundBooking = bs.findById(bookingId);
    //         foundBooking.setStatus(booking.getStatus());
    //         updatedBooking = bs.updateBooking(
    //         foundOwner = os.getOwnerByUsernameAndId(owner.getUsername(), booking.getid);
    //     } catch (InvalidAuthenticationException e) {
    //         return new ResponseEntity<>(BAD_REQUEST);
    //     }
    //     return new ResponseEntity<>(updatedBooking, OK);
    // }
 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }
 
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }  
}