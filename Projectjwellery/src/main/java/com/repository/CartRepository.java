package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    Cart findByUserIdAndIsOrderPlaced(int userId,boolean isOrderPlaced);
}