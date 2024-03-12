package com.ms.externalservice;

import java.util.List;

import com.ms.entity.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


//@FeignClient(name="PRODUCT-SERVICE")

@Service
@FeignClient(url = "http://localhost:8090/api/product", value = "Product-Client")
public interface ProductClient {
	
	@GetMapping("/products/{merchantId}")
	List<Product> getProductsOfUser(@PathVariable String merchantId);
	
//	@PostMapping("/add")
//	ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO);
	
	@PostMapping("/add")
	public Product addProduct(Product product);
	
	
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable String id, Product product);
	
	@DeleteMapping("/delete/{id}")
	public void deleteRating(@PathVariable String id);
}
