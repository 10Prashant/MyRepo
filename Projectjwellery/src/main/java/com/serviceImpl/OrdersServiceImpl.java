package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dto.OrderResponseDTO;
import com.entity.Cart;
import com.entity.Customer;
import com.repository.CartRepository;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.entity.Orders;
import com.repository.OrdersRepository;
import com.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<OrderResponseDTO> orderResponseDTOS=new ArrayList<>();
        List<Orders> ordersList=ordersRepository.findAll();
        for(Orders o:ordersList) {
            int userId=o.getCart().getUserId();
            OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
            orderResponseDTO.setOrderId(o.getOrderId());
            orderResponseDTO.setDate(o.getDate());
            orderResponseDTO.setStatus(o.getStatus());
            orderResponseDTO.setCart(o.getCart());
            orderResponseDTO.setPayment(o.getPayment());
            Optional<Customer> customerOptional=customerService.getCustomerById(userId);
            if(customerOptional.isPresent()) {
                Customer customer=customerOptional.get();
                orderResponseDTO.setCustomerName(customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
            }
            orderResponseDTOS.add(orderResponseDTO);
        }
        return orderResponseDTOS;
    }

    @Override
    public Optional<Orders> getOrderById(int orderId) {
        return ordersRepository.findById(orderId);
    }

    @Override
    public Orders createOrder(Orders order) {
        Optional<Cart> cartOptional = cartRepository.findById(order.getCart().getCartId());
        if (cartOptional.isPresent()) {
            Cart cart=cartOptional.get();
            cart.setOrderPlaced(true);
            cartRepository.save(cart);
        }
        return ordersRepository.save(order);
    }

    @Override
    public Orders updateOrder(int orderId, Orders updatedOrder) {
        if (!ordersRepository.existsById(orderId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        updatedOrder.setOrderId(orderId);
        return ordersRepository.save(updatedOrder);
    }

    @Override
    public boolean deleteOrder(int orderId) {
        ordersRepository.deleteById(orderId);
        return true;
    }

    @Override
    public List<Orders> getAllOrdersByUserId(int userId) {
        List<Orders> orders=ordersRepository.findAll();
        List<Orders> userOrder=new ArrayList<>();
        for(Orders o:orders) {
            if(o.getCart().getUserId()==userId) {
                userOrder.add(o);
            }
        }
        return userOrder;
    }
}