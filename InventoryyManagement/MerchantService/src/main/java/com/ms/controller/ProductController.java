package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.entity.Product;
import com.ms.externalservice.ProductClient;

@RestController
@RequestMapping(value="/api/user-product")
public class ProductController {
	
	private ProductClient productClient;
	
	@Autowired	
	public void setProductClient(ProductClient productClient) {
		this.productClient = productClient;
	}


	@PostMapping("/add")
	public String addProduct(@RequestBody Product product) {
		productClient.addProduct(product);
		return "Product Added To Merchant Inventory";
	}	
	
}
