package com.revature.airbnb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.airbnb.services.OwnerService;

@RestController
@RequestMapping("/owners")
@ResponseBody

public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

}
