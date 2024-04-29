package com.revature.airbnb.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.airbnb.DAOs.BookingDAO;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Exceptions.*;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    /*This method returns a Booking object by its id.
     * If the Booking is not found, a BookingNotFoundException is thrown.
     */
    public Booking findById(int id) throws BookingNotFoundException {
        return bookingDAO.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking with id " + id + " not found"));
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }
    
    /*This method creates a new Booking object.
     * The booking object should have a renter, listing, and start and end dates.
     * If any of these fields are missing... 
     */
    public Booking createBooking(Booking booking)  {
        return bookingDAO.save(booking);
    }

    /*This method updates a Booking object.
     * If the Booking is not found, a BookingNotFoundException is thrown.
     */
    public Booking updateBooking(Booking booking) throws BookingNotFoundException {
        bookingDAO.findById(booking.getBookingId())
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + booking.getBookingId()));
        bookingDAO.save(booking);
        return booking;
    }
}
