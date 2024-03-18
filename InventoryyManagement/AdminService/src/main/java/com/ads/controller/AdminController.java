package com.ads.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.dto.AdminDTO;
import com.ads.externalservices.MerchantClient;
import com.ads.externalservices.ProductClient;
import com.ads.pojo.MerchantDTO;
import com.ads.pojo.Product;
import com.ads.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
	
	private AdminService adminService;
	private ProductClient productClient;
	private MerchantClient merchantClient;
	
	@Autowired	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@Autowired
	public void setProductClient(ProductClient productClient) {
		this.productClient = productClient;
	}

	@Autowired
	public void setMerchantClient(MerchantClient merchantClient) {
		this.merchantClient = merchantClient;
	}



	@PostMapping("/register")
	public ResponseEntity<AdminDTO> registerAdmin(@RequestBody AdminDTO adminDTO){
		return new ResponseEntity<>(adminService.addAdmin(adminDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AdminDTO> getAdminById(@PathVariable String id){
		return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AdminDTO>> getAllAdmin(){
		return new ResponseEntity<>(adminService.getAllAdmin(), HttpStatus.FOUND);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AdminDTO> updateAdmin(@PathVariable String id, @Valid @RequestBody AdminDTO adminDTO) {
		return new ResponseEntity<AdminDTO>(adminService.updateAdmin(id, adminDTO), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable String id) {
		adminService.deleteAdmin(id);
		return new ResponseEntity<String>("Admin with ID: "+id+" Removed successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct(){
		return new ResponseEntity<>(productClient.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/getAllMerchant")
	public ResponseEntity<List<MerchantDTO>> getAllMerchant(){
		return new ResponseEntity<>(merchantClient.getAllMerchant(), HttpStatus.OK);
	}
	
}
