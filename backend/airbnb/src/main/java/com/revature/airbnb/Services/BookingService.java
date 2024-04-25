package com.revature.airbnb.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.airbnb.DAOs.BookingDAO;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Models.Renter;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }

    public List<Booking> getBookingsByRenter(Renter renter) {
        return bookingDAO.findByRenter(renter);
    }

    public List<Booking> getBookingsByOwner(Owner owner) {
        return bookingDAO.findByOwner(owner);
    }
}
