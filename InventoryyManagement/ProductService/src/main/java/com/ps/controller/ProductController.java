package com.ps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.dto.ProductDTO;
import com.ps.entity.Product;
import com.ps.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO){
		return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
	}
	
	//Get All Products of Specific User
	@GetMapping
	("/products/{merchantId}")
	public List<Product> getProductsOfUser(@PathVariable String merchantId){
		return productService.getProductsOfUser(merchantId);
	}
	
	
	
}	
