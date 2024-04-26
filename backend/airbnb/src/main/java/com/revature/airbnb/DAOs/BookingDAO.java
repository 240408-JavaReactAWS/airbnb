package com.revature.airbnb.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.Models.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer>{
    
    public List<Booking> findByRenterId(int userId);

    @Query("SELECT b from Booking b JOIN Listing L ON b.listingId = L.listingId WHERE L.ownerId = :ownerId")
    public List<Booking> findByOwnerId(int ownerId);
}