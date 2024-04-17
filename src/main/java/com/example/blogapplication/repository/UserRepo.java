package com.example.blogapplication.repository;

import com.example.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserEmail(String email);
}
