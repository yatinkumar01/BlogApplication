package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.CategoryException;
import com.example.blogapplication.exceptions.PostException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(Integer userId, Integer categoryId, Post posts) throws UserException, CategoryException;

    Post getPostById(Integer postId) throws PostException;

    List<Post> getAllPosts() throws PostException;

    Post updatePosts(int postID,Post posts) throws PostException;

    Post deletePost(Integer postId) throws PostException;
}
