package com.revature.airbnb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.airbnb.Exceptions.InvalidAuthenticationException;
import com.revature.airbnb.Exceptions.InvalidRegistrationException;
import com.revature.airbnb.Exceptions.UserNotFoundException;
import com.revature.airbnb.Exceptions.UsernameAlreadyTakenException;
import com.revature.airbnb.Models.Renter;
import com.revature.airbnb.Services.RenterService;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/renters")
public class RenterController {

    private final RenterService renterService;

    @Autowired
    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    @PostMapping("/register")
    public ResponseEntity<Renter> registerRenter(@RequestBody Renter renter) {
        Renter savedRenter;

        try {
            savedRenter = renterService.registerRenter(renter.getUsername(), renter.getPassword(), renter.getEmail());
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(savedRenter, CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Renter> loginHandler(@RequestBody Renter owner)
    {
        return ResponseEntity.ok(renterService.login(owner.getUsername(), owner.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Renter> logoutHandler(@RequestBody String token)
    {
        System.out.println(token);
        return ResponseEntity.ok(renterService.logout(token));
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

