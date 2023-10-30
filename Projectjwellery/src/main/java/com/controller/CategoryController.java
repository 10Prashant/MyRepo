package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.advices.CategoryException;
import com.entity.Category;
import com.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getallcategories")
    public ResponseEntity<List<Category>> getAllCategories() throws Throwable{
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/getcategorybyid/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId) throws CategoryException {
        Category category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/createcategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Throwable{
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category updatedCategory) throws Exception {
        Category updated = categoryService.updateCategory(categoryId, updatedCategory);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/del/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId) throws Exception {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/getbyname/{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) throws CategoryException {
        Category category = categoryService.getCategoryByName(categoryName);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
