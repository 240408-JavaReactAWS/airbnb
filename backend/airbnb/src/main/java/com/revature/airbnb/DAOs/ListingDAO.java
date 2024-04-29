package com.revature.airbnb.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.airbnb.Models.Listing;

@Repository
public interface ListingDAO extends JpaRepository<Listing, Integer> {
}