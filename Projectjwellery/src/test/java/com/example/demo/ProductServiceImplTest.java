package com.example.demo;

import com.entity.Category;
import com.entity.Product;
import com.repository.ProductRepository;
import com.serviceImpl.ProductServiceImpl;

 

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

 

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

 

class ProductServiceImplTest {

 

 

    @Mock
    private ProductRepository productRepository;

 

 

    @InjectMocks
    private ProductServiceImpl productService;

 

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testGetProductById() throws Throwable {
        // Arrange
        int productId = 1;
        Product mockProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act
        Optional<Product> result = productService.getProductById(productId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockProduct, result.get());
    }
    
    @Test
    void testGetProductByCategory() {
        // Arrange
        Category category = new Category();
        List<Product> mockProductsList = new ArrayList<>();
        when(productRepository.findByCategory(category)).thenReturn(mockProductsList);

        // Act
        List<Product> result = productService.getProductByCategory(category);

        // Assert
        assertEquals(mockProductsList, result);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product mockProduct = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

 

 

        // Act
        Product result = productService.createProduct(mockProduct);

        // Assert
        assertEquals(mockProduct, result);
    }

 

 

    @Test
    void testUpdateProduct() {
        // Arrange
        int productId = 1;
        Product mockProduct = new Product();
        when(productRepository.existsById(productId)).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

 

 

        // Act
        Product updatedProduct = productService.updateProduct(productId, mockProduct);

 

 

        // Assert
        assertEquals(mockProduct, updatedProduct);
    }

 

 

    @Test
    void testUpdateProductNotFound() {
        // Arrange
        int productId = 1;
        Product mockProduct = new Product();
        when(productRepository.existsById(productId)).thenReturn(false);

 

 

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> productService.updateProduct(productId, mockProduct));
    }
}