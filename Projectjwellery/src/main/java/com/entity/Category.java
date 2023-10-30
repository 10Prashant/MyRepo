package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @NotBlank(message = "Category name is required")
    @Size(max = 255, message = "Category name must be less than or equal to 255 characters")
    private String categoryName;

    @OneToMany(mappedBy = "category")
	@JsonIgnore
    private List<Product> product;

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public int getId() {
		return categoryId;
	}

	public void setId(int id) {
		this.categoryId = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    
}