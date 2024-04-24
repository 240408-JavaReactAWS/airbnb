package com.revature.airbnb.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.models.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer>{
    
}
