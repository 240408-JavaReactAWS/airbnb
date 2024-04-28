package com.revature.airbnb.Services;

import java.util.List;
import java.util.Optional;
import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
import org.springframework.stereotype.Service;
import com.revature.airbnb.DAOs.RenterDAO;
import com.revature.airbnb.Models.Renter;

@Service
public class RenterService {
    private final RenterDAO renterDAO;

    public RenterService(RenterDAO renterDAO) {
        this.renterDAO = renterDAO;
    }

    // register a renter account
    public Renter registerRenter(String username, String password, String email) throws UsernameAlreadyTakenException {
        // validate no renter exists by the username
        Optional<Renter> possibleRenter = renterDAO.findRenterByUsername(username);
        if (possibleRenter.isPresent()) {
            throw new UsernameAlreadyTakenException("Username: " + username + " was already taken");
        }
        Renter newRenter = new Renter(username, password, email, null);
        return renterDAO.save(newRenter);
    }

    public Renter getRenterById(int id) throws UserNotFoundException {
        return renterDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public Renter getRenterByToken(String token) throws UserNotFoundException {
        return renterDAO.findByToken(token).orElseThrow(() -> new UserNotFoundException("User not found with token: " + token));
    }

    public List<Renter> getAllRenters() {
        return renterDAO.findAll();
    }

    public Renter login(String username, String password) throws InvalidAuthenticationException {
        Renter toRet = renterDAO.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidAuthenticationException(
            "That username/password combination is not present in the database."));
        toRet.generateToken();
        //toRet.setToken(username);
        renterDAO.save(toRet);
        return toRet;
    }

    public Renter logout(String token) throws InvalidAuthenticationException {
        Renter toRet = renterDAO.findByToken(token).orElseThrow(()-> new InvalidAuthenticationException("Could not find user for corresponding token."));
        toRet.setToken(null);
        renterDAO.save(toRet);
        return toRet;
    }
}
