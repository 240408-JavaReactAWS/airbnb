package com.revature.airbnb.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.airbnb.DAOs.BookingDAO;
import com.revature.airbnb.Models.Booking;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }
    
    public Booking createBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    /*This function retrieves all Bookings by their associated Renter ID */
    public List<Booking> getBookingsByRenterId(int renterId) {
        return bookingDAO.findByRenterId(renterId);
    }
}
