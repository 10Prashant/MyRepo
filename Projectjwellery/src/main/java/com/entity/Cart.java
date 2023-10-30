package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int cartQuantity;
    private double totalprice;
	private boolean isOrderPlaced;

    private int userId;

	public boolean isOrderPlaced() {
		return isOrderPlaced;
	}

	public void setOrderPlaced(boolean orderPlaced) {
		isOrderPlaced = orderPlaced;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public List<Product> getProduct() {
//		return product;
//	}
//
//	public void setProduct(List<Product> product) {
//		this.product = product;
//	}
}