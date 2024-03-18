package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.externalservices.AdminClient;
import com.auth.externalservices.MerchantClient;
import com.auth.services.AuthService;

@RestController
@RequestMapping(value = "/api/auth-admin")
public class AuthAdminController {
	
	private AuthService authService;
	
	@Autowired	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable String id) {
		authService.deleteAuthData(id);
		return new ResponseEntity<String>("Admin with ID: "+id+" Removed successfully",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete-merchant/{id}")
	public ResponseEntity<String> deleteMerchant(@PathVariable String id){
		authService.deleteAuthData(id);
		return new ResponseEntity<String>("Merchant with ID: "+id+" Removed successfully",HttpStatus.OK);
	}
	
}
