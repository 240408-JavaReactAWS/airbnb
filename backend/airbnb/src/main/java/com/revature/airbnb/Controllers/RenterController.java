package com.revature.airbnb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
import com.revature.airbnb.Models.Renter;
import com.revature.airbnb.Services.RenterService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/renters")
public class RenterController {

    private final RenterService renterService;

    @Autowired
    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    @PostMapping
    public ResponseEntity<Renter> registerRenter(@RequestBody Renter renter) {
        Renter savedRenter;

        try {
            savedRenter = renterService.registerRenter(renter.getUsername(), renter.getPassword(), renter.getEmail());
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(savedRenter, CREATED);
    }

    @GetMapping
    public List<Renter> getAllRenters() {
        return renterService.getAllRenters();
    }

    @GetMapping("{id}")
    public ResponseEntity<Renter> getRenterById(@PathVariable int id) {
        try {
            Renter renter = renterService.getRenterById(id);
            return new ResponseEntity<>(renter, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

}

