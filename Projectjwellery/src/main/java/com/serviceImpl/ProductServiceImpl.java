package com.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.advices.CartException;
import com.advices.ProductException;
import com.entity.Category;
import com.entity.Product;
import com.repository.ProductRepository;
import com.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository prorepo;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProduct() throws Throwable {
        List<Product> products = prorepo.findAll();
        if (products.isEmpty()) {
            throw new ProductException("No Products found");
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(int productId) throws Throwable {
        Optional<Product> product = prorepo.findById(productId);
        if (!product.isPresent()) {
            throw new CartException("Product with ID " + productId + " not found");
        }
        return product;
    }    
        
    @Override
    public List<Product> getProductByCategory(Category category) {
        List<Product> products = prorepo.findByCategory(category);
        if (products.isEmpty()) {
            // You can choose to return an empty list or throw an exception here
            // For this example, I'm returning an empty list
            return Collections.emptyList();
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return prorepo.save(product);
    }

    @Override
    public Product updateProduct(int productId, Product updatedProduct) {
        if (!prorepo.existsById(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Item not found");
        }
        updatedProduct.setProductId(productId);
        return prorepo.save(updatedProduct);
    }

    @Override
    public void deleteProduct(int productId) throws ProductException {
        if (!prorepo.existsById(productId)) {
            throw new ProductException("Cart with ID " + productId + " not found");
        }
        prorepo.deleteById(productId);
    }
    
    @Override
    public Optional<List<Product>> getProductByName(String productName) throws Throwable {
        Optional<List<Product>> product = prorepo.findByProductName(productName);
        if (!product.isPresent()) {
            throw new ProductException("Product with name '" + productName + "' not found");
        }
        return product;
    }
}
