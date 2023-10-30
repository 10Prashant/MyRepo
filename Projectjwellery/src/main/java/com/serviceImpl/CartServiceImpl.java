package com.serviceImpl;

import java.util.*;

//import javax.validation.Valid;

import com.dto.CartDTO;
import com.dto.CartResponseDTO;
import com.entity.CartProduct;
import com.entity.Product;
import com.repository.CartProductRepository;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.CartException;
import com.entity.Cart;
import com.repository.CartRepository;
import com.service.CartService;

import jakarta.validation.Valid;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Cart> getAllCarts() throws Throwable {
        List<Cart> carts = cartRepository.findAll();
        if (carts.isEmpty()) {
            throw new CartException("No Carts found");
        }
        return carts;
    }

    @Override
    public Optional<Cart> getCartById(int cartId) throws Throwable {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (!cart.isPresent()) {
            throw new CartException("Cart with ID " + cartId + " not found");
        }
        return cart;
    }

    @Override
    public Cart createCart(@Valid CartDTO cartDTO) throws CartException {
        if (cartDTO.getCartProductList().size() == 0) {
            return null;
        }
        Cart cart = new Cart();
        cart.setCartId(cartDTO.getCartId());
        cart.setCartQuantity(cartDTO.getCartQuantity());
        cart.setOrderPlaced(cartDTO.isOrderPlaced());
        cart.setTotalprice(cartDTO.getTotalprice());
        cart.setUserId(cartDTO.getUserId());
        Cart createdCart = cartRepository.save(cart);
        List<CartProduct> cartProductList = cartProductRepository.findAllByCartId(cartDTO.getCartId());
        if (cartProductList != null) {
            for (int i = 0; i < cartProductList.size(); i++) {
                cartProductRepository.delete(cartProductList.get(i));
            }
        }
        for (int i = 0; i < cartDTO.getCartProductList().size(); i++) {
            CartProduct cartProduct = cartDTO.getCartProductList().get(i);
            cartProduct.setCartId(createdCart.getCartId());
            cartProductRepository.save(cartProduct);
        }
        return createdCart;
    }

    @Override
    public Cart updateCart(int cartId, @Valid Cart updatedCart) throws CartException {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart with ID " + cartId + " not found");
        }
        updatedCart.setCartId(cartId);
        return cartRepository.save(updatedCart);
    }

    @Override
    public void deleteCart(int cartId) throws CartException {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart with ID " + cartId + " not found");
        }
        List<CartProduct> cartProductList = cartProductRepository.findAllByCartId(cartId);
        for (CartProduct cartProduct : cartProductList) {
            cartProductRepository.delete(cartProduct);
        }
        cartRepository.deleteById(cartId);
    }

    @Override
    public Map<String, Object> getActiveCart(int userId) throws Throwable {
        List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();
        Cart cart = cartRepository.findByUserIdAndIsOrderPlaced(userId, false);
        if (cart != null) {
            List<CartProduct> cartProductList = cartProductRepository.findAllByCartId(cart.getCartId());

            if (cartProductList != null) {
                for (int i = 0; i < cartProductList.size(); i++) {
                    CartResponseDTO cartResponseDTO = new CartResponseDTO();
                    CartProduct cartProduct = cartProductList.get(i);
                    Optional<Product> productOptional = productService.getProductById(cartProduct.getProductId());
                    if (productOptional.isPresent()) {
                        Product product = productOptional.get();
                        cartResponseDTO.setProductId(product.getProductId());
                        cartResponseDTO.setProductName(product.getProductName());
                        cartResponseDTO.setQuantity(product.getQuantity());
                        cartResponseDTO.setProductImage(product.getProductImage());
                        cartResponseDTO.setPrice(product.getPrice());
                        cartResponseDTO.setQty(cartProduct.getQuantity());
                        cartResponseDTOList.add(cartResponseDTO);
                    }
                }
            }
        }
        Map<String, Object> responseCartMap = new HashMap<>();
        if (cartResponseDTOList.size() == 0) {
            responseCartMap.put("cartId", 0);
            responseCartMap.put("cartProduct", cartResponseDTOList);
        } else {
            responseCartMap.put("cartId", cart.getCartId());
            responseCartMap.put("cartProduct", cartResponseDTOList);
        }
        return responseCartMap;
    }
}
