package com.example.blogapplication.service;


import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.User;
import com.example.blogapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> optionalUser = userRepo.findById(user.getUserId());
        if (optionalUser.isEmpty()) return userRepo.save(user);
        else throw new UserException("User already registered with id " + user.getUserId());
    }

    @Override
    public List<User> getAllUsers() throws UserException {
        List<User> users = userRepo.findAll();
        if (!users.isEmpty()) return users;
        else throw new UserException("User Not Found");
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepo.findByUserEmail(email);
        if (user == null) throw new UserException("User Not Found --> " + email);
        else return user;
    }
}
