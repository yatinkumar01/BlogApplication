package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.CategoryException;
import com.example.blogapplication.exceptions.PostException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Category;
import com.example.blogapplication.model.Post;
import com.example.blogapplication.model.User;
import com.example.blogapplication.repository.CategoryRepo;
import com.example.blogapplication.repository.PostRepo;
import com.example.blogapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    final CategoryRepo categoryRepo;
    final PostRepo postRepo;
    final UserRepo userRepo;

    @Autowired
    public PostServiceImpl(CategoryRepo categoryRepo, PostRepo postRepo, UserRepo userRepo) {
        this.categoryRepo = categoryRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Post createPost(Integer userID, Integer categoryID, Post post) throws UserException, CategoryException {
        if (post == null) throw new IllegalArgumentException("Post object cannot be null");

        User user = userRepo.findById(userID)
                .orElseThrow(() -> new UserException("User doesn't exist with id " + userID));

        Category category = categoryRepo.findById(categoryID)
                .orElseThrow(() -> new CategoryException("Category doesn't exist with id " + categoryID));

        post.setUser(user);
        post.setCategory(category);
        return postRepo.save(post);
    }

    @Override
    public Post getPostById(Integer postID) throws PostException {
        return postRepo.findById(postID)
                .orElseThrow(() -> new PostException("Post doesn't exist with id " + postID));
    }

    @Override
    public List<Post> getAllPosts() throws PostException {
        List<Post> posts = postRepo.findAll();
        if (posts.isEmpty()) throw new PostException("No Post Found");
        else return posts;
    }

    @Override
    public Post updatePosts(int postID, Post posts) throws PostException {
        Post existPost = postRepo.findById(postID)
                .orElseThrow(() -> new PostException("Post doesn't exist with id " + postID));

        existPost.setPostTitle(posts.getPostTitle());
        existPost.setPostDesc(posts.getPostDesc());
        return postRepo.save(existPost);
    }

    @Override
    public Post deletePost(Integer postId) throws PostException {
        return postRepo.findById(postId)
                .map(post -> {
                    postRepo.deleteById(postId);
                    return post;
                })
                .orElseThrow(() -> new PostException("Post doesn't exist with id " + postId));
    }
}
