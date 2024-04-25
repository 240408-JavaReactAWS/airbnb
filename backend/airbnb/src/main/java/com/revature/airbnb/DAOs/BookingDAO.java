package com.revature.airbnb.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.Models.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer>{
    
}