package com.revature.airbnb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.airbnb.daos.OwnerDAO;
import com.revature.airbnb.exceptions.InvalidAuthenticationException;
import com.revature.airbnb.exceptions.InvalidRegistrationException;
import com.revature.airbnb.exceptions.UserAlreadyExistsException;
import com.revature.airbnb.exceptions.UserNotFoundException;
import com.revature.airbnb.models.Owner;

@Service
public class OwnerService {

    @Autowired
    private OwnerDAO renterDAO;
    
    public OwnerService(OwnerDAO renterDAO) {
        this.renterDAO = renterDAO;
    }

    public List<Owner> getAllOwners()
    {
        return renterDAO.findAll();
    }

    public Owner getOwnerById(int id) throws UserNotFoundException
    {
        return renterDAO.findById(id).orElseThrow(() -> new UserNotFoundException("Owner not found!"));
    }
    public Owner getOwnerByOwnerName(String username) throws UserNotFoundException
    {
        return renterDAO.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Owner not found!"));
    }

    //TO-DO: Re-write register, login, logout to include session info

    public Owner registerOwner(Owner renter) throws InvalidRegistrationException, UserAlreadyExistsException
    {
        String username = renter.getUsername();
        String password = renter.getPassword();
        if(username == null || password == null)
        {
            throw new InvalidRegistrationException("Unable to register new user:" + 
                username + ". Ownername and password must be specified.");
        }
        if(username.length() < 4 || password.length() < 4)
        {
            throw new InvalidRegistrationException("Unable to register new user:" + 
                username + ". Ownername and password must be at least four characters.");
        }
        else if(renterDAO.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsException("Owner " + username + " already exists!");
        }
        else
        {
            return renterDAO.save(renter);
        }
    }

    public Owner login(String username, String password) throws InvalidAuthenticationException
    {
        Owner toRet = renterDAO.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidAuthenticationException(
            "That username/password combination is not present in the database."));
        renterDAO.save(toRet);
        return toRet;
    }

    /*
    public Owner logout(String token) throws InvalidAuthenticationException
    {
        Owner toRet = renterDAO.findByToken(token).orElseThrow(()-> new InvalidAuthenticationException("Could not find user for corresponding token."));
        renterDAO.save(toRet);
        return toRet;
    }
    */
}