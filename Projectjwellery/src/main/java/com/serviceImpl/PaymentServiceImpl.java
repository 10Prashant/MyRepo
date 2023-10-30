package com.serviceImpl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.entity.Payment;
import com.repository.PaymentRepository;
import com.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private PaymentRepository paymentrepo;

    @Override
    public Optional<Payment> getPaymentById(int paymentId) {
        return paymentrepo.findById(paymentId);
    }

    @Override
    public Payment updatePayment(int paymentId, Payment updatepayment) {
        if (!paymentrepo.existsById(paymentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment Declined");
        }
        updatepayment.setPaymentId(paymentId);
        return paymentrepo.save(updatepayment);
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentrepo.save(payment);
    }
}
