package com.service;

import java.util.Optional;
import com.entity.Payment;

public interface PaymentService {
	
	public Optional<Payment> getPaymentById(int paymentId);
	public Payment createPayment(Payment payment);
	public Payment updatePayment(int paymentId,Payment updatepayment);

}
