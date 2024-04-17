package com.example.blogapplication.service;

import com.example.blogapplication.exceptions.CategoryException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Category;
import com.example.blogapplication.model.User;
import com.example.blogapplication.repository.CategoryRepo;
import com.example.blogapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryRepo categoryRepo;
    final UserRepo userRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, UserRepo userRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Category createCategory(Integer userID, Category category) throws UserException {
        Optional<User> userOptional = userRepo.findById(userID);
        if (userOptional.isEmpty()) {
            throw new UserException("User doesn't exist with id " + userID);
        } else {
            return categoryRepo.save(category);
        }
    }

    @Override
    public Category getCategoryById(Integer categoryID) throws CategoryException {
        return categoryRepo.findById(categoryID)
                .orElseThrow(() -> new CategoryException("Category doesn't exist with id " + categoryID));
    }

    @Override
    public Category updateCategory(Integer categoryID, Category category) throws CategoryException {
        if (category == null) {
            throw new IllegalArgumentException("Category object cannot be null");
        }
        return categoryRepo.findById(categoryID)
                .map(existedCategory -> {
                    existedCategory.setCategoryName(category.getCategoryName());
                    return categoryRepo.save(existedCategory);
                })
                .orElseThrow(() -> new CategoryException("Category doesn't exist with id " + categoryID));
    }

    @Override
    public Category deleteCategory(Integer categoryID) throws CategoryException {
        return categoryRepo.findById(categoryID)
                .map(category -> {
                    categoryRepo.deleteById(categoryID);
                    return category;
                })
                .orElseThrow(() -> new CategoryException("Category doesn't exist with id " + categoryID));
    }

    @Override
    public List<Category> getAllCategory() throws CategoryException {
        List<Category> categoryList = categoryRepo.findAll();
        if (categoryList.isEmpty()) {
            throw new CategoryException("No Category Exist");
        } else {
            return categoryList;
        }
    }
}
