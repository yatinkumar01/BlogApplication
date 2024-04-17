package com.example.blogapplication.repository;

import com.example.blogapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
