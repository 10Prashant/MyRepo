package com.repository;

import java.util.Optional;

import com.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByAdminName(String adminName);
	Optional<Admin> findByEmailId(String emailId);
}
