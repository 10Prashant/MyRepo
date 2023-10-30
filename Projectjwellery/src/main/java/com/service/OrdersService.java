package com.service;

import java.util.List;
import java.util.Optional;

import com.dto.OrderResponseDTO;
import com.entity.Orders;
import com.entity.Product;


public interface OrdersService {

    public List<OrderResponseDTO> getAllOrders();

    public List<Orders> getAllOrdersByUserId(int userId);

    public Optional<Orders> getOrderById(int orderId);

    public Orders createOrder(Orders order);

    public Orders updateOrder(int orderId, Orders updatedOrder);

    public boolean deleteOrder(int orderId);


}
