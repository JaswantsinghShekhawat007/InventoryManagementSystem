package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.entity.Product;
import com.ms.externalservice.ProductClient;
import com.ms.security.JwtTokenProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/user-product")
public class ProductController {
	
	private ProductClient productClient;
	
	private JwtTokenProvider jwtTokenProvider;
	

	@Autowired
	public void setProductClient(ProductClient productClient) {
		this.productClient = productClient;
	}

	@Autowired
	public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/add")
	public String addProduct(@RequestBody Product product, @RequestHeader("Authorization") String authorizationHeader) {
		
		String token = jwtTokenProvider.extractJwtToken(authorizationHeader);
		
		String username = jwtTokenProvider.getUsername(token);
		
//		merchantProductService.addMerchantProduct(username, product);
		
		product.setMerchantId(username);
		
		productClient.addProduct(product);
		
		if(product.getMerchantId()==null) throw new RuntimeException("Merchant Id cannot be Null");
		
		return "Product Added To Merchant Inventory";
	}	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateCustomer(@PathVariable String id, @Valid @RequestBody Product product) {
		return new ResponseEntity<Product>(productClient.updateProduct(id, product), HttpStatus.OK);
	}
	
	@GetMapping("/products/{merchantId}")
	public List<Product> getProductsOfUser(@PathVariable String merchantId){
		return productClient.getProductsOfUser(merchantId);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
		productClient.deleteProduct(id);
		return new ResponseEntity<String>("User with ID: "+id+" deleted successfully",HttpStatus.OK);
	}
	
}
