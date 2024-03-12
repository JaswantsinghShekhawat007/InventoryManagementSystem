package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.entity.Product;
import com.ms.externalservice.ProductClient;
import com.ms.security.JwtTokenProvider;

@RestController
@RequestMapping(value="/api/user-product")
public class ProductController {
	
//	private productExternalService productExternalService;
	
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
		
		product.setMerchantId(username);
		
		if(product.getMerchantId()==null) throw new RuntimeException("Merchant Id cannot be Null");
		
		productClient.addProduct(product);
		return "Product Added To Merchant Inventory";
	}	
	
}
