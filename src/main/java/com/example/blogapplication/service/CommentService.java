package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.CommentException;
import com.example.blogapplication.exceptions.PostException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Integer userId, Integer postId, Comment comment) throws UserException, PostException;

    Comment updateComment(Integer commentID, Comment comment) throws CommentException;

    Comment deleteComment(Integer commentID) throws CommentException;

}
