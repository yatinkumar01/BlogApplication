package com.example.blogapplication.controller;

import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.User;
import com.example.blogapplication.repository.UserRepo;
import com.example.blogapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;
    final UserRepo usersRepository;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserRepo usersRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUsers(@RequestBody User users) throws UserException {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return new ResponseEntity<>(userService.registerUser(users), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> logInUserHandler(Authentication auth) {
        User userOptional = usersRepository.findByUserEmail(auth.getName());
        if (userOptional == null) throw new RuntimeException("User Not Found");
        return new ResponseEntity<>(userOptional.getUserName() + "LogIn Successful", HttpStatus.ACCEPTED);
    }

    @GetMapping("/AllUsers")
    public ResponseEntity<List<User>> getAllUsers() throws UserException {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUsers/{email}")
    public ResponseEntity<User> getUsersByEmail(@PathVariable String email) throws UserException {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.ACCEPTED);
    }
}
