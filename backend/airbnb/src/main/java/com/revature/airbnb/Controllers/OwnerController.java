package com.revature.airbnb.Controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Exceptions.InvalidRegistrationException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Services.OwnerService;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Owner> registerOwner(@RequestBody Owner owner) {
        Owner savedOwner;

        try {
            savedOwner = ownerService.registerOwner(owner.getUsername(), owner.getPassword(), owner.getEmail());
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST); // returning 500 internal error when supposed to return 400 bad request
        }
        return new ResponseEntity<>(savedOwner, CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Owner> loginHandler(@RequestBody Owner owner)
    {
        return ResponseEntity.ok(ownerService.login(owner.getUsername(), owner.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Owner> logoutHandler(@RequestBody String token)
    {
        return ResponseEntity.ok(ownerService.logout(token));
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> viewAccountDetails(@PathVariable int id) {
        try {
            Owner owner = ownerService.getOwnerById(id);
            Map<String, Object> accountDetails = new LinkedHashMap<>();
            accountDetails.put("username", owner.getUsername());
            accountDetails.put("email", owner.getEmail());
            accountDetails.put("listings", owner.getListings());
            return new ResponseEntity<>(accountDetails, OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @GetMapping("{id}/listings")
    public List<Listing> getAllOwners(@PathVariable int id) {
        return ownerService.getOwnerListings(id);
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleUsernameAlreadyTaken(UsernameAlreadyTakenException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String InvalidAuthenticationHandler(InvalidAuthenticationException e)
    {
        return e.getMessage();
    }
}
