package com.revature.airbnb.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.models.Renter;

@Repository
public interface RenterDAO extends JpaRepository<Renter, Integer> {
    public Optional<Renter> findByUsername(String username);
    public Optional<Renter> findByUsernameAndPassword(String username, String password);
}
