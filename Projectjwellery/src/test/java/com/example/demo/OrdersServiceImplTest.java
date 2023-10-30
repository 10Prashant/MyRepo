package com.example.demo;

import com.dto.OrderResponseDTO;
import com.entity.Orders;
import com.entity.Product; // Import the Product class or the appropriate class for food items
import com.repository.OrdersRepository;
import com.serviceImpl.OrdersServiceImpl;

 

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

 

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

 

class OrdersServiceImplTest {

 

 

    @Mock
    private OrdersRepository ordersRepository;

 

 

    @InjectMocks
    private OrdersServiceImpl ordersService;

 

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

 

    @Test
    void testGetAllOrders() {
        // Arrange
        List<Orders> mockOrdersList = new ArrayList<>();
        when(ordersRepository.findAll()).thenReturn(mockOrdersList);

 

 

        // Act
        List<OrderResponseDTO> result = ordersService.getAllOrders();

 

 

        // Assert
        assertEquals(mockOrdersList, result);
    }

 

 

    @Test
    void testGetOrderById() {
        // Arrange
        int orderId = 1;
        Orders mockOrder = new Orders();
        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

 

 

        // Act
        Optional<Orders> result = ordersService.getOrderById(orderId);

 

 

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockOrder, result.get());
    }

 

 

    @Test
    void testGetOrderByIdNotFound() {
        // Arrange
        int orderId = 1;
        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());

 

 

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> ordersService.getOrderById(orderId));
    }

 

 

    @Test
    void testCreateOrder() {
        // Arrange
        Orders mockOrder = new Orders(); // Create a mock order
        when(ordersRepository.save(any(Orders.class))).thenReturn(mockOrder);

 

 

        // Act
        Orders result = ordersService.createOrder(mockOrder);

 

 

        // Assert
        // Add assertions for the result based on your business logic
        // For example:
        // assertEquals(expectedOrderId, result.getId());
    }

 

 

    @Test
    void testUpdateOrder() {
        // Arrange
        int orderId = 1;
        Orders mockOrder = new Orders(); // Create a mock order
        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

 

 

        // Act
        Orders result = ordersService.updateOrder(orderId, mockOrder);

 

 

        // Assert
        // Add assertions for the result based on your business logic
        // For example:
        // assertEquals(expectedUpdatedOrder, result);
    }

 

 

    @Test
    void testDeleteOrder() {
        // Arrange
        int orderId = 1;

 

 

        // Act & Assert
        assertDoesNotThrow(() -> ordersService.deleteOrder(orderId));
    }
}