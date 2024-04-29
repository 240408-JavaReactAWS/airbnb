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

    public Renter getRenterByUsernameAndId(String username, int id) throws UserNotFoundException, InvalidAuthenticationException {
        Optional<Renter> optRenterByUsername = renterDAO.findRenterByUsername(username);
        Optional<Renter> optRenterById = renterDAO.findById(id);
        if (optRenterByUsername.isEmpty() || optRenterById.isEmpty()) {
            throw new UserNotFoundException("No renter found with username " + username + " or id " + id);
        }
        Renter returnedRenterByUsername = optRenterByUsername.get();
        Renter returnedRenterById = optRenterById.get();
        if (returnedRenterByUsername.equals(returnedRenterById)) {
            return returnedRenterById;
        }
        throw new InvalidAuthenticationException("Incorrect renter attempting to access account details!");
    }

    public List<Renter> getAllRenters() {
        return renterDAO.findAll();
    }

    public Renter login(String username, String password) throws InvalidAuthenticationException, UserNotFoundException {
        Optional<Renter> optRenter = renterDAO.findByUsernameAndPassword(username, password);
        if (optRenter.isEmpty()) {
            throw new UserNotFoundException("No User found with username: " + username);
        }
        Renter foundRenter = optRenter.get();
        if (password.equals(foundRenter.getPassword())){
            return foundRenter;
        } else {
            throw new InvalidAuthenticationException("Username or password was incorrect!");
        }
    }
}
