package com.revature.airbnb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.airbnb.daos.RenterDAO;
import com.revature.airbnb.exceptions.InvalidAuthenticationException;
import com.revature.airbnb.exceptions.InvalidRegistrationException;
import com.revature.airbnb.exceptions.UserAlreadyExistsException;
import com.revature.airbnb.exceptions.UserNotFoundException;
import com.revature.airbnb.models.Renter;

@Service
public class RenterService {

    @Autowired
    private RenterDAO renterDAO;;
    
    public RenterService(RenterDAO renterDAO) {
        this.renterDAO = renterDAO;
    }

    public List<Renter> getAllUsers()
    {
        return renterDAO.findAll();
    }

    public Renter getUserById(int id) throws UserNotFoundException
    {
        return renterDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
    public Renter getUserByUserName(String username) throws UserNotFoundException
    {
        return renterDAO.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    //TO-DO: Re-write register, login, logout to include session info

    public Renter registerUser(Renter renter) throws InvalidRegistrationException, UserAlreadyExistsException
    {
        String username = renter.getUsername();
        String password = renter.getPassword();
        if(username == null || password == null)
        {
            throw new InvalidRegistrationException("Unable to register new user:" + 
                username + ". Username and password must be specified.");
        }
        if(username.length() < 4 || password.length() < 4)
        {
            throw new InvalidRegistrationException("Unable to register new user:" + 
                username + ". Username and password must be at least four characters.");
        }
        else if(renterDAO.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsException("User " + username + " already exists!");
        }
        else
        {
            return renterDAO.save(renter);
        }
    }

    public Renter login(String username, String password) throws InvalidAuthenticationException
    {
        Renter toRet = renterDAO.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidAuthenticationException(
            "That username/password combination is not present in the database."));
        renterDAO.save(toRet);
        return toRet;
    }

    /*
    public Renter logout(String token) throws InvalidAuthenticationException
    {
        User toRet = renterDAO.findByToken(token).orElseThrow(()-> new InvalidAuthenticationException("Could not find user for corresponding token."));
        renterDAO.save(toRet);
        return toRet;
    }
    */
}