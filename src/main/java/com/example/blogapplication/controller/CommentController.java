package com.example.blogapplication.controller;

import com.example.blogapplication.exceptions.CommentException;
import com.example.blogapplication.exceptions.PostException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Comment;
import com.example.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{userID}/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Integer userID, @PathVariable Integer postId, @RequestBody Comment comment) throws PostException, UserException {
        return new ResponseEntity<>(commentService.createComment(userID, postId, comment), HttpStatus.OK);
    }

    @DeleteMapping("/{commentID}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer commentID) throws CommentException {
        return new ResponseEntity<>(commentService.deleteComment(commentID), HttpStatus.OK);
    }

    @PatchMapping("/{commentID}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer commentID, @RequestBody Comment comment) throws CommentException {
        return new ResponseEntity<>(commentService.updateComment(commentID, comment), HttpStatus.OK);
    }
}
