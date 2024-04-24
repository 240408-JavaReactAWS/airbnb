package com.revature.airbnb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.airbnb.exceptions.InvalidAuthenticationException;
import com.revature.airbnb.exceptions.InvalidRegistrationException;
import com.revature.airbnb.exceptions.UserAlreadyExistsException;
import com.revature.airbnb.exceptions.UserNotFoundException;
import com.revature.airbnb.models.Owner;
import com.revature.airbnb.services.OwnerService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/owners")
@ResponseBody

public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping()
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(int id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping()
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.registerOwner(owner);
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleUserAlreadyExists(UserAlreadyExistsException e)
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
    public @ResponseBody String handleInvalidUser(InvalidAuthenticationException e)
    {
        return e.getMessage();
    }
}
