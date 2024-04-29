package com.revature.airbnb.DAOs;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.Models.Renter;

@Repository
public interface RenterDAO extends JpaRepository<Renter, Integer>{

    Optional<Renter> findRenterByUsername(String username);
    Optional<Renter> findByUsernameAndPassword(String username, String password);
    // Optional<Renter> findByToken(String token);
}