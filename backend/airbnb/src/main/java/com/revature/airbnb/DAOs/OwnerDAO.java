package com.revature.airbnb.DAOs;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.Models.Owner;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Integer>{

    Optional<Owner> findOwnerByUsername(String username);
    Optional<Owner> findByUsernameAndPassword(String username, String password);
}