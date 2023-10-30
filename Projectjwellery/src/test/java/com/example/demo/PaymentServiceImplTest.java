package com.example.demo;

import com.entity.Payment;
import com.repository.PaymentRepository;
import com.serviceImpl.PaymentServiceImpl;

 

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

 

 

import java.util.Optional;

 

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

 

class PaymentServiceImplTest {

 

 

    @Mock
    private PaymentRepository paymentRepository;

 

 

    @InjectMocks
    private PaymentServiceImpl paymentService;

 

 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

 

    @Test
    void testGetPaymentById() {
        // Arrange
        int paymentId = 1;
        Payment mockPayment = new Payment();
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(mockPayment));

 

 

        // Act
        Optional<Payment> result = paymentService.getPaymentById(paymentId);

 

 

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockPayment, result.get());
    }

 

 

    @Test
    void testCreatePayment() {
        // Arrange
        Payment mockPayment = new Payment();
        when(paymentRepository.save(any(Payment.class))).thenReturn(mockPayment);

 

 

        // Act
        Payment result = paymentService.createPayment(mockPayment);

 

 

        // Assert
        assertEquals(mockPayment, result);
    }

 

 

    @Test
    void testUpdatePayment() {
        // Arrange
        int paymentId = 1;
        Payment mockPayment = new Payment();
        when(paymentRepository.existsById(paymentId)).thenReturn(true);
        when(paymentRepository.save(any(Payment.class))).thenReturn(mockPayment);

 

 

        // Act
        Payment updatedPayment = paymentService.updatePayment(paymentId, mockPayment);

 

 

        // Assert
        assertEquals(mockPayment, updatedPayment);
    }
}