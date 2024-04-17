package com.example.blogapplication.controller;

import com.example.blogapplication.exceptions.CategoryException;
import com.example.blogapplication.exceptions.UserException;
import com.example.blogapplication.model.Category;
import com.example.blogapplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/{userID}")
    public ResponseEntity<Category> createCategory(@PathVariable Integer userID, @RequestBody Category category) throws UserException {
        return new ResponseEntity<>(categoryService.createCategory(userID, category), HttpStatus.OK);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryID) throws CategoryException {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryID), HttpStatus.OK);
    }

    @PutMapping("/{categoryID}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryID, @RequestBody Category category) throws CategoryException {
        return new ResponseEntity<>(categoryService.updateCategory(categoryID, category), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryID}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer categoryID) throws CategoryException {
        return new ResponseEntity<>(categoryService.deleteCategory(categoryID), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() throws CategoryException {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
}
