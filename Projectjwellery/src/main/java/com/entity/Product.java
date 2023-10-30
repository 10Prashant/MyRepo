package com.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must be less than or equal to 255 characters")
    private String productName;

    @ManyToOne
    private Category category;

    @PositiveOrZero(message = "Quantity must be a positive number or zero")
    private int quantity;

    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    private double price;

    @NotBlank(message = "Product image URL is required")
    @Size(max = 255, message = "Product image URL must be less than or equal to 255 characters")
    private String productImage;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
//	@Override
//	public String toString() {
//		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
//				+ ", quantity=" + quantity + ", price=" + price + ", productImage=" + productImage + "]";
//	}
//	
}
