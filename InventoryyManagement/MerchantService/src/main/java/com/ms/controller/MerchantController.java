package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ms.dto.MerchantDTO;
import com.ms.entity.Merchant;
import com.ms.externalservice.ProductClient;
import com.ms.service.MerchantService;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/merchant")
public class MerchantController {
	
	private MerchantService merchantService;
	private ProductClient productClient;

	@Autowired
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
	
	@Autowired	
	public void setProductClient(ProductClient productClient) {
		this.productClient = productClient;
	}



	@PostMapping("/register")
	public ResponseEntity<MerchantDTO> registerMerchant(@Valid @RequestBody MerchantDTO merchantDTO){
		return new ResponseEntity<>(merchantService.registerUser(merchantDTO), HttpStatus.CREATED);
	}	
	
	@GetMapping("/get-merchant/{id}")
	public ResponseEntity<MerchantDTO> getCustomerById(@PathVariable String id) {
		return new ResponseEntity<MerchantDTO>(merchantService.getUserById(id), HttpStatus.OK);
	}

	@GetMapping("/get-merchant/all")
	public ResponseEntity<List<Merchant>> getAllCustomers() {
		return new ResponseEntity<List<Merchant>>(merchantService.getAllUser(),HttpStatus.OK);
	}
	
	@PutMapping("/update-merchant/{id}")
	public ResponseEntity<MerchantDTO> updateCustomer(@PathVariable String id, @Valid @RequestBody MerchantDTO merchantDTO) {
		return new ResponseEntity<MerchantDTO>(merchantService.updateUser(id, merchantDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-merchant/{id}")
	public ResponseEntity<String> deleteMerchant(@PathVariable String id) {
		productClient.deleteMerchantProduct(id);
		merchantService.deleteUser(id);
		return new ResponseEntity<String>("Merchant with ID: "+id+" deleted successfully",HttpStatus.OK);
	}
}
