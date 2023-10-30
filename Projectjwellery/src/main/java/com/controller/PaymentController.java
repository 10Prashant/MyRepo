package com.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.advices.CartException;
import com.advices.PaymentException;
import com.entity.Payment;
import com.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/getpaymentbyid/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int paymentId) throws Throwable{
        Optional<Payment> payment = paymentService.getPaymentById(paymentId);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createpayment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) throws PaymentException{
        Payment createdPayment = paymentService.createPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @PutMapping("/updatePayment/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int paymentId, @RequestBody Payment updatedPayment) throws PaymentException{
        Payment updated = paymentService.updatePayment(paymentId, updatedPayment);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
