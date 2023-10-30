package com.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Payment {
	
	@Id
    private int paymentId;

    @NotBlank(message = "Payment status is required")
    @Size(max = 255, message = "Payment status must be less than or equal to 255 characters")
    private String status;

    @NotBlank(message = "Payment medium is required")
    @Size(max = 255, message = "Payment medium must be less than or equal to 255 characters")
    private String medium;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", status=" + status + ", medium=" + medium + "]";
	}
}