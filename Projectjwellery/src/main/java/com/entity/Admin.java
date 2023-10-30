package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Admin extends User{

	@NotBlank(message = "Admin name is required")
	@Size(max = 100, message = "Admin name must be less than or equal to 100 characters")
	private String adminName;

	/*
	 * @NotBlank(message = "Location is required")
	 * 
	 * @Size(max = 255, message =
	 * "Location must be less than or equal to 255 characters") private String
	 * location;
	 */
	@NotNull(message = "Contact number is required")
	private Long contactNo;
	
	//
//	@Email(message = "Invalid email format")
//	@NotBlank(message = "Email is required")
//	@Size(max = 100, message = "Email must be less than or equal to 100 characters")
	private String emailId;
	
public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
