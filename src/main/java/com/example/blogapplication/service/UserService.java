package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.User;

import java.util.List;

public interface UserService {

    User registerUser(User users) throws UserException;

    List<User> getAllUsers() throws UserException;

    User getUserByEmail(String email) throws UserException;
}
