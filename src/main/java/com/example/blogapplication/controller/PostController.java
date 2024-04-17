package com.example.blogapplication.controller;

import com.example.blogapplication.exceptions.CategoryException;
import com.example.blogapplication.exceptions.PostException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Post;
import com.example.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create/{userId}/{categoryId}")
    public ResponseEntity<Post> createPosts(@PathVariable Integer userId, @PathVariable Integer categoryId, @RequestBody Post post) throws CategoryException, UserException {
        return new ResponseEntity<>(postService.createPost(userId, categoryId, post), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postID}")
    public ResponseEntity<Post> deletePosts(@PathVariable Integer postID) throws PostException {
        return new ResponseEntity<>(postService.deletePost(postID), HttpStatus.OK);
    }

    @GetMapping("/get/{postID}")
    public ResponseEntity<Post> getPostsById(@PathVariable Integer postID) throws PostException {
        return new ResponseEntity<>(postService.getPostById(postID), HttpStatus.OK);
    }

    @PutMapping("/update/{postID}")
    public ResponseEntity<Post> updatePosts(@PathVariable Integer postID, @RequestBody Post post) throws PostException {
        return new ResponseEntity<>(postService.updatePosts(postID, post), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getAllPosts() throws PostException {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
}
