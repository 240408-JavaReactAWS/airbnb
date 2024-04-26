package com.revature.airbnb.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.airbnb.DAOs.BookingDAO;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Exceptions.BookingNotFoundException;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public Booking findById(int id) throws BookingNotFoundException {
        return bookingDAO.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking with id " + id + " not found"));
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }
    
    public Booking createBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    public Booking updateBookingStatus(Booking booking, String status) {
        bookingDAO.findById(booking.getBookingId())
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + booking.getBookingId()));
        booking.setStatus(status);
        return bookingDAO.save(booking);
    }

    /*This function retrieves all Bookings by their associated Renter ID */
    public List<Booking> getBookingsByRenterId(int renterId) {
        return bookingDAO.findByRenterId(renterId);
    }

    /*This function retrieves all Bookings by their associated Owner ID */
    public List<Booking> getBookingsByOwnerId(int ownerId) {
        return bookingDAO.findByOwnerId(ownerId);
    }
}
