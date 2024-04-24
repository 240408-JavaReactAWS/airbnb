package com.revature.airbnb.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.airbnb.DAOs.OwnerDAO;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Models.Owner;

@Service
public class OwnerService {
    private final OwnerDAO ownerDAO;

    public OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    public List<Owner> getAllOwners() {
        return ownerDAO.findAll();
    }

    public Owner createOwner(Owner owner) {
        return ownerDAO.save(owner);
    }

    public Owner getOwnerById(int id) {
        return ownerDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}
