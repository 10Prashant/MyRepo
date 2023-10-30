package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

 

import com.entity.User;

 

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);
	Optional<List<User>> findByUsername(String username);
	User findByUserId(int userId);

}
