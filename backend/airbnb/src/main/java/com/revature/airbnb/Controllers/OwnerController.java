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
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Services.OwnerService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> registerOwner(@RequestBody Owner owner) {
        Owner savedOwner;

        try {
            savedOwner = ownerService.registerOwner(owner.getUsername(), owner.getPassword(), owner.getEmail());
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST); // returning 500 internal error when supposed to return 400 bad request
        }
        return new ResponseEntity<>(savedOwner, CREATED);
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable int id) {
        try {
            Owner owner = ownerService.getOwnerById(id);
            return new ResponseEntity<>(owner, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }


}
