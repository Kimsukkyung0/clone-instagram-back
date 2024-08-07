package com.example.cloneinstagramback.controller;

import com.example.cloneinstagramback.exception.UserException;
import com.example.cloneinstagramback.insta.modal.User;
import com.example.cloneinstagramback.repository.UserRepository;
import com.example.cloneinstagramback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRep;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
        User regUser = userService.registerUser(user);
        return new ResponseEntity<User>(regUser, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<User> signInHandler(Authentication auth) throws BadCredentialsException {
            Optional<User> opt = userRep.findByEmail(auth.getName());

            if(opt.isPresent()){
                return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
            }
            throw new BadCredentialsException("Invalid email or password");
    }
}
