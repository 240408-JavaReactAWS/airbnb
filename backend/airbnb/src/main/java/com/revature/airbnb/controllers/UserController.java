package com.revature.airbnb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.airbnb.exceptions.*;
import com.revature.airbnb.models.User;
import com.revature.airbnb.services.UserService;

@RestController
@RequestMapping("/users")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user)
    {
        return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginHandler(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.login(user.getUsername(), user.getPassword()));
    }

    /*
    @PostMapping("/logout")
    public ResponseEntity<User> logoutHandler(@RequestParam String token)
    {
        return ResponseEntity.ok(userService.logout(token));
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