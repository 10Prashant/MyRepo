package com.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.entity.Product;
import com.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getproduct")
    public ResponseEntity<List<Product>> getAllProduct() throws Throwable{
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getproductbyid/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) throws Throwable{
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createproduct")
    public ResponseEntity<?> createProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If validation errors exist, return a 400 Bad Request response with the error details.
            return ResponseEntity.badRequest().body("Validation error: " + bindingResult.getFieldError().getDefaultMessage());
        }

        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }


    @PutMapping("/updateproductbyid/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product updatedProduct) throws Throwable{
        Product updated = productService.updateProduct(productId, updatedProduct);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteproductbyid/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) throws Throwable{
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/getproductbyname/{productName}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String productName) throws Throwable {
        Optional<List<Product>> product = productService.getProductByName(productName);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
