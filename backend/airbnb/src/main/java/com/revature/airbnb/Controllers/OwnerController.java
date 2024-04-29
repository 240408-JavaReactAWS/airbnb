package com.revature.airbnb.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.airbnb.Exceptions.*;
import com.revature.airbnb.Models.*;
import com.revature.airbnb.Services.*;
import jakarta.servlet.http.HttpSession;
import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
@RestController
@RequestMapping("owners")
public class OwnerController {

    private final OwnerService os;

    @Autowired
    public OwnerController(OwnerService os) {
        this.os = os;
    }

    /* Creates new Owner */
    @PostMapping("register")
    public ResponseEntity<Owner> registerOwner(@RequestBody Owner owner, HttpSession session) {
        Owner savedOwner;
        try {
            savedOwner = os.registerOwner(owner.getUsername(), owner.getPassword(), owner.getEmail());
            session.setAttribute("owner", savedOwner); // Store the owner in the session
        } catch (UsernameAlreadyTakenException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(savedOwner, CREATED);
    }

    /* Login existing Owner using HttpSession */
    @PostMapping("login")
    public ResponseEntity<Owner> loginHandler(@RequestBody Owner owner, HttpSession session) {
        Owner loggedInOwner;
        try {
            loggedInOwner = os.login(owner.getUsername(), owner.getPassword());
            session.setAttribute("owner", loggedInOwner); // Store the owner in the session
        } catch (InvalidAuthenticationException | UserNotFoundException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(loggedInOwner, OK);
    }

    /* Logout existing Owner using HttpSession */
    @PostMapping("logout")
    public ResponseEntity<Void> logoutHandler(HttpSession session) {
        session.removeAttribute("owner"); // Remove the owner from the session
        session.invalidate();
        return new ResponseEntity<>(OK);
    }

    @GetMapping("session")
    public ResponseEntity<Owner> validateSession(HttpSession session) {
        Owner owner = (Owner) session.getAttribute("owner");
        if (owner == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        return new ResponseEntity<>(owner, OK);
    }

    /* GET /owners */
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        return new ResponseEntity<>(os.getAllOwners(), OK);
    }

    /* GET /owners/id */
    @GetMapping("{id}")
    public ResponseEntity<Owner> viewAccountDetails(@RequestHeader(name = "owner", required = true) String username, HttpSession session, @PathVariable int id) {
        Owner owner = (Owner) session.getAttribute("owner");
        if (owner == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        Owner foundOwner;
        try {
            foundOwner = os.getOwnerByUsernameAndId(owner.getUsername(), id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(FORBIDDEN);
        } catch (InvalidAuthenticationException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(foundOwner, OK);
    }
  
    /* GET /owners/id/listings */
    @GetMapping("{id}/listings")
    public ResponseEntity<List<Listing>> getListings(@PathVariable int id, HttpSession session) {
        Owner owner = (Owner) session.getAttribute("owner");
        if (owner == null) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
        Owner foundOwner;
        try {
            foundOwner = os.getOwnerByUsernameAndId(owner.getUsername(), id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(FORBIDDEN);
        } catch (InvalidAuthenticationException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(foundOwner.getListings(), OK);
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String handleUsernameAlreadyTaken(UsernameAlreadyTakenException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody String InvalidAuthenticationHandler(InvalidAuthenticationException e) {
        return e.getMessage();
    }
}
