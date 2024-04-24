package com.revature.airbnb.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.airbnb.models.Owner;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
    public Optional<Owner> findByUsername(String username);
    public Optional<Owner> findByUsernameAndPassword(String username, String password);
}
