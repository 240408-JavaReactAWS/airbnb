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

    public Booking findById(int id) throws BookingNotFoundException {
        return bookingDAO.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking with id " + id + " not found"));
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }
    
    public Booking createBooking(Booking booking) throws InvalidAuthenticationException  {
        return bookingDAO.save(booking);
    }

    public Booking updateBooking(Booking booking) throws BookingNotFoundException {
        bookingDAO.findById(booking.getBookingId())
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + booking.getBookingId()));
        bookingDAO.save(booking);
        return booking;
    }
}
