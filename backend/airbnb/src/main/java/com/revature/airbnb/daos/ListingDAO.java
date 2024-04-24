package com.revature.airbnb.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.airbnb.models.Listing;

public interface ListingDAO extends JpaRepository<Listing, Integer>{
    
}
