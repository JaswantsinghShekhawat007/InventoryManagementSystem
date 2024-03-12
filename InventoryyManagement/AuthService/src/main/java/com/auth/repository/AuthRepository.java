package com.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.entity.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String>{

	Optional<Auth> findByUserIdOrEmail(String userId, String email);
	
	Boolean existsByEmail(String email);  
	
}
