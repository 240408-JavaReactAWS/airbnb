package com.revature.airbnb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.airbnb.exceptions.*;
import com.revature.airbnb.services.RenterService;
import com.revature.airbnb.models.Renter;

@RestController
@RequestMapping("/renters")
@ResponseBody
public class RenterController {
    
    @Autowired
    private RenterService renterService;

    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    @GetMapping()
    public ResponseEntity<List<Renter>> getAllRenters()
    {
        return new ResponseEntity<List<Renter>>(renterService.getAllUsers(), HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Renter> registerRenterHandler(@RequestBody Renter user)
    {
        return new ResponseEntity<Renter>(renterService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Renter> loginHandler(@RequestBody Renter user)
    {
        return ResponseEntity.ok(renterService.login(user.getUsername(), user.getPassword()));
    }

    /*
    @PostMapping("/logout")
    public ResponseEntity<Renter> logoutHandler(@RequestParam String token)
    {
        return ResponseEntity.ok(renterService.logout(token));
    }
    */

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleRenterAlreadyExists(UserAlreadyExistsException e)
    {
        return e.getMessage();
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleMessageNotFound(UserNotFoundException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleInvalidRenter(InvalidAuthenticationException e)
    {
        return e.getMessage();
    }
}