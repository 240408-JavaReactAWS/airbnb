package com.revature.airbnb.Services;

import java.util.List;
import java.util.Optional;

import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;

import org.springframework.stereotype.Service;

import com.revature.airbnb.DAOs.RenterDAO;
import com.revature.airbnb.Models.Booking;
import com.revature.airbnb.Models.Renter;

@Service
public class RenterService {
    private final RenterDAO renterDAO;

    public RenterService(RenterDAO renterDAO) {
        this.renterDAO = renterDAO;
    }

    /*public Renter createRenter(Renter renter) {
        return renterDAO.save(renter);
    }*/

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

    public Renter getRenterById(int id) {
        return renterDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public List<Renter> getAllRenters() {
        return renterDAO.findAll();
    }
}
