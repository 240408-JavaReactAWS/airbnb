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
import com.revature.airbnb.Models.Owner;
import com.revature.airbnb.Services.OwnerService;

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
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerService.createOwner(owner);
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
