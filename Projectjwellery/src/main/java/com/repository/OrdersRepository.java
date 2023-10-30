package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	 List<Orders> findByCart_CartId(int cartId);
	 
}