package com.revature.airbnb.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.airbnb.daos.UserDAO;
import com.revature.airbnb.exceptions.InvalidAuthenticationException;
import com.revature.airbnb.exceptions.InvalidRegistrationException;
import com.revature.airbnb.exceptions.UserAlreadyExistsException;
import com.revature.airbnb.exceptions.UserNotFoundException;
import com.revature.airbnb.models.User;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;;
    
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers()
    {
        return userDAO.findAll();
    }

    public User getUserById(int id) throws UserNotFoundException
    {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
    public User getUserByUserName(String username) throws UserNotFoundException
    {
        return userDAO.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    //TO-DO: Re-write register, login, logout to include session info

    public User registerUser(User user) throws InvalidRegistrationException, UserAlreadyExistsException
    {
        String username = user.getUsername();
        String password = user.getPassword();
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
        else if(userDAO.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsException("User " + username + " already exists!");
        }
        else
        {
            return userDAO.save(user);
        }
    }

    public User login(String username, String password) throws InvalidAuthenticationException
    {
        User toRet = userDAO.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidAuthenticationException(
            "That username/password combination is not present in the database."));
        userDAO.save(toRet);
        return toRet;
    }

    /*
    public User logout(String token) throws InvalidAuthenticationException
    {
        User toRet = userDAO.findByToken(token).orElseThrow(()-> new InvalidAuthenticationException("Could not find user for corresponding token."));
        userDAO.save(toRet);
        return toRet;
    }
    */
}