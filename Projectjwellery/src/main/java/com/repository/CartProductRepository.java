package com.repository;

import com.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {

    List<CartProduct> findAllByCartId(int cartId);
}
