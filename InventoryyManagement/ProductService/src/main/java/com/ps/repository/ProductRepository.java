package com.ps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
	List<Product> findByMerchantId(String userId);
	
	void deleteAllByMerchantId(String merchantId);
	
}
