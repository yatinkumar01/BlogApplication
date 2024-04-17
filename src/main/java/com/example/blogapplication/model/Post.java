package com.example.blogapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postID;

    @NotBlank(message = "Post title is required")
    @Size(max = 255, message = "Post title cannot be longer than 255 characters")
    private String postTitle;

    @NotBlank(message = "Post description is required")
    private String postDesc;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_ID")
    private Category category;

    @Min(value = 0, message = "Post likes count cannot be negative")
    private Integer postLikes = 0;
}
