package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity

public class Customer extends User {

	 @NotBlank(message = "First name is required")
	    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
	 private String customerFirstName;
	 
	 @NotBlank(message = "Last name is required")
	    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
	    private String customerLastName;
	 
	 @NotBlank(message = "Address is required")
	    @Size(max = 255, message = "Address must be less than or equal to 255 characters")
	    private String address;
	 
	 @NotNull(message = "Mobile number is required")
	    private Long mobilenumber;
	 
	 	@Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
	    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
	    private String emailId;

	    @OneToOne(cascade=CascadeType.ALL)
	    //@JsonBackReference // To avoid circular reference
	    private Orders orders;


		public String getCustomerFirstName() {
			return customerFirstName;
		}

		public void setCustomerFirstName(String customerFirstName) {
			this.customerFirstName = customerFirstName;
		}

		public String getCustomerLastName() {
			return customerLastName;
		}

		public void setCustomerLastName(String customerLastName) {
			this.customerLastName = customerLastName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Long getMobilenumber() {
			return mobilenumber;
		}

		public void setMobilenumber(Long mobilenumber) {
			this.mobilenumber = mobilenumber;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public Orders getOrders() {
			return orders;
		}

		public void setOrders(Orders orders) {
			this.orders = orders;
		}

}
