package com.example.demo;

import com.advices.CategoryException;
import com.entity.Category;
import com.repository.CategoryRepository;
import com.serviceImpl.CategoryServiceImpl;

 

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

 

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

 

class CategoryServiceImplTest {

 

 

    @Mock
    private CategoryRepository categoryRepository;

 

 

    @InjectMocks
    private CategoryServiceImpl categoryService;

 

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

 

    @Test
    void testGetAllCategories() {
        // Arrange
        List<Category> mockCategoriesList = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(mockCategoriesList);

 

 

        // Act
        List<Category> result = categoryService.getAllCategories();

 

 

        // Assert
        assertEquals(mockCategoriesList, result);
    }

 

 

    @Test
    void testGetCategoryById() throws CategoryException {
        // Arrange
        int categoryId = 1;
        Category mockCategory = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

 

 

        // Act
        Category result = categoryService.getCategoryById(categoryId);

 

 

        // Assert
        assertEquals(mockCategory, result);
    }

 


    @Test
    void testCreateCategory() {
        // Arrange
        Category mockCategory = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

 

 

        // Act
        Category result = categoryService.createCategory(mockCategory);

 

 

        // Assert
        assertEquals(mockCategory, result);
    }

 

 

    @Test
    void testUpdateCategory() throws Exception {
        // Arrange
        int categoryId = 1;
        Category mockCategory = new Category();
        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

 

 

        // Act
        Category updatedCategory = categoryService.updateCategory(categoryId, mockCategory);

 

 

        // Assert
        assertEquals(mockCategory, updatedCategory);
    }

 

 

  
    @Test
    void testDeleteCategory() {
        // Arrange
        int categoryId = 1;
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

 

 

        // Act
        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryId));
    }

 

 

    
}
