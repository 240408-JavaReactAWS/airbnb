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

    public Owner registerOwner(String username, String password, String email) throws UsernameAlreadyTakenException {
        // validate no owner exists by your username
        Optional<Owner> possibleOwner = ownerDAO.findOwnerByUsername(username);
        if (possibleOwner.isPresent()) {
            throw new UsernameAlreadyTakenException("Username: " + username + " was already taken");
        }
        Owner newOwner = new Owner(username, password, email, null);
        return ownerDAO.save(newOwner);
    }

    public Owner getOwnerByUsernameAndId(String username, int id) throws UserNotFoundException, InvalidAuthenticationException {
        Optional<Owner> optOwnerByUsername = ownerDAO.findOwnerByUsername(username);
        Optional<Owner> optOwnerById = ownerDAO.findById(id);
        if (optOwnerByUsername.isEmpty() || optOwnerById.isEmpty()) {
            throw new UserNotFoundException("No owner found with username " + username + " or id " + id);
        }
        Owner returnedOwnerByUsername = optOwnerByUsername.get();
        Owner returnedOwnerById = optOwnerById.get();
        if (returnedOwnerByUsername.equals(returnedOwnerById)) {
            return returnedOwnerById;
        }
        throw new InvalidAuthenticationException("Incorrect owner attempting to access account details!");
    }

    public List<Owner> getAllOwners() {
        return ownerDAO.findAll();
    }

    public Owner login(String username, String password) throws InvalidAuthenticationException, UserNotFoundException {
        Optional<Owner> optOwner = ownerDAO.findByUsernameAndPassword(username, password);
        if (optOwner.isEmpty()) {
            throw new UserNotFoundException("No User found with username: " + username);
        }
        Owner foundOwner = optOwner.get();
        if (password.equals(foundOwner.getPassword())){
            return foundOwner;
        } else {
            throw new InvalidAuthenticationException("Username or password was incorrect!");
        }
    }
}
