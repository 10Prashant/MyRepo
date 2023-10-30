package com.example.demo;

import com.advices.CustomerException;
import com.entity.Customer;
import com.repository.CustomerRepository;
import com.serviceImpl.CustomerServiceImpl;

 

 

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

 

 

class CustomerServiceImplTest {

 

 

    @Mock
    private CustomerRepository customerRepository;

 

 

    @InjectMocks
    private CustomerServiceImpl customerService;

 

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

 

    @Test
    void testGetAllCustomers() {
        // Arrange
        List<Customer> mockCustomersList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(mockCustomersList);

 

 

        // Act
        List<Customer> result = customerService.getAllCustomers();

 

 

        // Assert
        assertEquals(mockCustomersList, result);
    }

 

 

    @Test
    void testGetCustomerById() {
        // Arrange
        int customerId = 1;
        Customer mockCustomer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

 

 

        // Act
        Optional<Customer> result = customerService.getCustomerById(customerId);

 

 

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockCustomer, result.get());
    }

 

 

    @Test
    void testGetCustomerByIdNotFound() {
        // Arrange
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

 

 

        // Act & Assert
        assertFalse(customerService.getCustomerById(customerId).isPresent());
    }

 

 

    @Test
    void testCreateCustomer() throws CustomerException {
        // Arrange
        Customer mockCustomer = new Customer();
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

 

 

        // Act
        Customer result = customerService.createCustomer(mockCustomer);

 

 

        // Assert
        assertEquals(mockCustomer, result);
    }

 

 

    @Test
    void testUpdateCustomer() {
        // Arrange
        int customerId = 1;
        Customer mockCustomer = new Customer();
        when(customerRepository.existsById(customerId)).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

 

 

        // Act
        Customer updatedCustomer = customerService.updateCustomer(customerId, mockCustomer);

 

 

        // Assert
        assertEquals(mockCustomer, updatedCustomer);
    }

 

 

    @Test
    void testUpdateCustomerNotFound() {
        // Arrange
        int customerId = 1;
        Customer mockCustomer = new Customer();
        when(customerRepository.existsById(customerId)).thenReturn(false);

 

 

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> customerService.updateCustomer(customerId, mockCustomer));
    }

 

 

    @Test
    void testDeleteCustomer() {
        // Arrange
        int customerId = 1;
        when(customerRepository.existsById(customerId)).thenReturn(true);

 

 

        // Act
        assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));
    }

 

 

    
}