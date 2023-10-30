package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//import javax.validation.Valid;

import com.dto.CartDTO;
import com.dto.CartResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.advices.CartException;
import com.entity.Cart;
import com.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getallcarts")
    public ResponseEntity<List<Cart>> getAllCarts() throws Throwable {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/getcartbyid/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int cartId) throws Throwable {
        Optional<Cart> cart = cartService.getCartById(cartId);
        return cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createCart")
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cart) throws CartException {
        Cart createdCart = cartService.createCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PutMapping("/updateCart/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable int cartId, @RequestBody @Valid Cart updatedCart) throws CartException {
        Cart cart = cartService.updateCart(cartId, updatedCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/deletecart/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartId) throws CartException {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getactivecart/{userId}")
    public ResponseEntity<Map<String,Object>> getActiveCart(@PathVariable int userId) throws Throwable {
        Map<String,Object> cartResponseDTOList=cartService.getActiveCart(userId);
        return new ResponseEntity<Map<String,Object>>(cartResponseDTOList,HttpStatus.OK);
    }
}
