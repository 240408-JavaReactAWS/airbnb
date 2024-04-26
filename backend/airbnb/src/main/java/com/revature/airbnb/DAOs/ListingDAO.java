package com.revature.airbnb.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Owner;

@Repository
public interface ListingDAO extends JpaRepository<Listing, Integer>{

        public List<Listing> findByOwner(Owner owner);
    
}