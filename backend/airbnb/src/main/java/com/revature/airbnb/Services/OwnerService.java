package com.revature.airbnb.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.airbnb.DAOs.OwnerDAO;
import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
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

    public Owner registerOwner(String username, String password, String email) throws UsernameAlreadyTakenException{
        
        // validate no owner exists by your username
        Optional<Owner> possibleOwner = ownerDAO.findOwnerByUsername(username);
        if (possibleOwner.isPresent()) {
            throw new UsernameAlreadyTakenException("Username: " + username + " was already taken");
        }
        Owner newOwner = new Owner(username, password, email, null);

        return ownerDAO.save(newOwner);
    }

    public Owner getOwnerById(int id) {
        return ownerDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public Owner getOwnerByToken(String token) {
        return ownerDAO.findByToken(token).orElseThrow(() -> new UserNotFoundException("User not found with token: " + token));
    }

    public Owner login(String username, String password) throws InvalidAuthenticationException
    {
        Owner toRet = ownerDAO.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidAuthenticationException(
            "That username/password combination is not present in the database."));

        toRet.generateToken();
        //toRet.setToken(username);
        ownerDAO.save(toRet);
        return toRet;
    }

    public Owner logout(String token) throws InvalidAuthenticationException
    {
        Owner toRet = ownerDAO.findByToken(token).orElseThrow(()-> new InvalidAuthenticationException("Could not find user for corresponding token."));
        toRet.setToken(null);
        ownerDAO.save(toRet);
        return toRet;
    }
}
