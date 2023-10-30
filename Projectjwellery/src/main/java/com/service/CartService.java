package com.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dto.CartDTO;
import com.dto.CartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.advices.CartException;
import com.entity.Cart;
import com.repository.CartRepository;

public interface CartService {

    public List<Cart> getAllCarts() throws Throwable ;
    public Optional<Cart> getCartById(int cartId) throws Throwable ;
    public Cart createCart(CartDTO cartDTO) throws CartException ;
    public Cart updateCart(int cartId, Cart updatedCart) throws CartException ;
    public void deleteCart(int cartId) throws CartException ;
    Map<String,Object> getActiveCart(int userId) throws Throwable;
}