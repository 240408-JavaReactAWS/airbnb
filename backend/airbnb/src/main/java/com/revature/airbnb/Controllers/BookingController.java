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
    private final ListingService ls;

    @Autowired
    public BookingController(BookingService bs, ListingService ls) {
        this.bs = bs;
        this.ls = ls;
    }

    /* GET /bookings */
    //QA: Should this endpoint be available for renters and owners?
    /* 
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bs.getAllBookings(), OK);
    }*/

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
        //Make sure the renter exists!
        if (renter == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }

        //Make sure the renter is the renter of the booking!
        if(renter.getUserId() != booking.getRenterId()) {
            return new ResponseEntity<>(FORBIDDEN);
        }

        //Make sure the listing exists!
        try{
            Listing mightBeAListing = ls.getListingById(booking.getListingId());
        } catch (ListingNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        Booking newBooking;
        newBooking = bs.createBooking(booking);
        return new ResponseEntity<>(newBooking, CREATED);
    }

    /* As an owner, update a booking's status */
    /* PATCH /bookings/id */
     @PatchMapping("/{bookingId}")
     public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, HttpSession session, @PathVariable int bookingId) {
        //Make sure the owner is logged in
         Owner owner = (Owner) session.getAttribute("owner");
         if (owner == null) {
             return new ResponseEntity<>(UNAUTHORIZED);
         }
         Booking updatedBooking;
         try {
             Listing listing = ls.getListingById(booking.getListingId());
             Booking foundBooking = null;
             for(Booking b : listing.getBookings())
             {
                    if(b.getBookingId() == bookingId)
                    {
                        foundBooking = b;
                        break;
                    }
             }
            if(foundBooking == null)
            {
                return new ResponseEntity<>(NOT_FOUND);
            }
             foundBooking.setStatus(booking.getStatus());
             updatedBooking = bs.updateBooking(foundBooking);
         }
            catch (BookingNotFoundException e) {
             return new ResponseEntity<>(NOT_FOUND);
            }
         return new ResponseEntity<>(updatedBooking, OK);
     }
 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }
 
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }  
}