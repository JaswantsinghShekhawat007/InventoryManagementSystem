package com.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.dto.AdminDTO;
import com.ads.service.AdminService;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/register")
	public ResponseEntity<AdminDTO> registerAdmin(@RequestBody AdminDTO adminDTO){
		return new ResponseEntity<>(adminService.addAdmin(adminDTO), HttpStatus.CREATED);
	}
}
