package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.CategoryException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Integer userId, Category category) throws UserException;

    Category getCategoryById(Integer categoryId) throws CategoryException;

    Category updateCategory(Integer categoryId, Category category) throws CategoryException;

    Category deleteCategory(Integer categoryId) throws CategoryException;

    List<Category> getAllCategory() throws CategoryException;
}
