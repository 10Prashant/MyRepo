package com.example.demo;

 

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

 

import com.advices.CartException;
import com.entity.Cart;
import com.repository.CartRepository;
import com.serviceImpl.CartServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

 

public class CartServiceImplTest {

 

    @Mock
    private CartRepository cartRepository;

 

    @InjectMocks
    private CartServiceImpl cartService;

 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

    @Test
    public void getAllCarts_WhenCartsExist_ShouldReturnAllCarts() throws Throwable {
        List<Cart> carts = Arrays.asList(new Cart(), new Cart());
        when(cartRepository.findAll()).thenReturn(carts);

 

        List<Cart> result = cartService.getAllCarts();

 

        assertNotNull(result);
        assertEquals(2, result.size());
    }

 

    @Test
    public void getAllCarts_WhenNoCartsExist_ShouldThrowException() throws Throwable {
        when(cartRepository.findAll()).thenReturn(Arrays.asList());

 

        assertThrows(CartException.class, () -> cartService.getAllCarts());
    }

 

    @Test
    public void getCartById_CartExists_ShouldReturnCart() throws Throwable {
        Cart cart = new Cart();
        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

 

        Optional<Cart> result = cartService.getCartById(1);

 

        assertTrue(result.isPresent());
        assertEquals(cart, result.get());
    }

 

    @Test
    public void getCartById_CartDoesNotExist_ShouldThrowException() throws Throwable {
        when(cartRepository.findById(1)).thenReturn(Optional.empty());

 

        assertThrows(CartException.class, () -> cartService.getCartById(1));
    }

 

    @Test
    public void createCart_ValidCart_ShouldReturnSavedCart() throws CartException {
        Cart cart = new Cart();
        when(cartRepository.save(cart)).thenReturn(cart);

 

//        Cart result = cartService.createCart(cart);

 

//        assertEquals(cart, result);
    }

 

    @Test
    public void updateCart_ValidCartId_ShouldReturnUpdatedCart() throws CartException {
        Cart updatedCart = new Cart();
        when(cartRepository.existsById(1)).thenReturn(true);
        when(cartRepository.save(updatedCart)).thenReturn(updatedCart);

 

        Cart result = cartService.updateCart(1, updatedCart);

 

        assertEquals(updatedCart, result);
    }

 

    @Test
    public void updateCart_InvalidCartId_ShouldThrowException() {
        Cart updatedCart = new Cart();
        when(cartRepository.existsById(1)).thenReturn(false);

 

        assertThrows(CartException.class, () -> cartService.updateCart(1, updatedCart));
    }

 

    @Test
    public void deleteCart_ValidCartId_ShouldDeleteWithoutErrors() {
        when(cartRepository.existsById(1)).thenReturn(true);
        doNothing().when(cartRepository).deleteById(1);

 

        assertDoesNotThrow(() -> cartService.deleteCart(1));
    }

 

    @Test
    public void deleteCart_InvalidCartId_ShouldThrowException() {
        when(cartRepository.existsById(1)).thenReturn(false);

 

        assertThrows(CartException.class, () -> cartService.deleteCart(1));
    }
}
