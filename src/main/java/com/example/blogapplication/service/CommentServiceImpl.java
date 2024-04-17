package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.CommentException;
import com.example.blogapplication.exceptions.PostException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Comment;
import com.example.blogapplication.model.Post;
import com.example.blogapplication.model.User;
import com.example.blogapplication.repository.CommentRepo;
import com.example.blogapplication.repository.PostRepo;
import com.example.blogapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    final UserRepo userRepo;
    final CommentRepo commentRepo;
    final PostRepo postRepo;

    @Autowired
    public CommentServiceImpl(UserRepo userRepo, CommentRepo commentRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public Comment createComment(Integer userID, Integer postID, Comment comment) throws UserException, PostException {
        User user = userRepo.findById(userID)
                .orElseThrow(() -> new UserException("User doesn't exist with id " + userID));

        Post post = postRepo.findById(postID)
                .orElseThrow(() -> new PostException("Post doesn't exist with id " + postID));

        comment.setUser(user);
        comment.setPost(post);
        return commentRepo.save(comment);
    }

    @Override
    public Comment updateComment(Integer commentID, Comment comment) throws CommentException {
        Comment existingComment = commentRepo.findById(commentID)
                .orElseThrow(() -> new CommentException("Comment doesn't exist with id " + commentID));

        existingComment.setCommentDesc(comment.getCommentDesc());
        return commentRepo.save(existingComment);
    }

    @Override
    public Comment deleteComment(Integer commentID) throws CommentException {
        Comment comment = commentRepo.findById(commentID)
                .orElseThrow(() -> new CommentException("Comment doesn't exist with id " + commentID));

        commentRepo.deleteById(commentID);
        return comment;
    }
}
