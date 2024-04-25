package com.revature.airbnb.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Models.Renter;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Integer>{

    public List<Booking> findByRenter(Renter renter);
    public List<Booking> findByOwner(Owner owner);
}