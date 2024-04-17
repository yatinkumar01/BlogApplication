package com.example.blogapplication.repository;

import com.example.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {


}
