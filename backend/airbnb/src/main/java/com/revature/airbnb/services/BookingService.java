package com.revature.airbnb.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.airbnb.daos.BookingDAO;
import com.revature.airbnb.models.Booking;

@Service
public class BookingService {
    
    private BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<Booking> getAllBookings()
    {
        return bookingDAO.findAll();
    }

    public Booking getBookingById(int id) {
        return bookingDAO.findById(id).get();
    }

    public Booking addBooking(Booking booking) {
        return bookingDAO.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingDAO.save(booking);
    } 
    public Booking deleteBooking(int id) {
        Booking booking = bookingDAO.findById(id).get();
        bookingDAO.deleteById(id);
        return booking;
    }
}
