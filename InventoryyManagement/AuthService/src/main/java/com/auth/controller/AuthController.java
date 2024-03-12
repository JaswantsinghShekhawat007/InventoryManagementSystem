package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.AuthDTO;
import com.auth.dto.JwtAuthResponse;
import com.auth.dto.LoginDTO;
import com.auth.services.AuthService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/auth")
public class AuthController {
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN','MERCHANT')")
	@PostMapping("/merchant/register")
	public ResponseEntity<AuthDTO> registerMerchant(@Valid @RequestBody AuthDTO authDTO){
		return new ResponseEntity<>(authService.addMerchantAuth(authDTO), HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/register")
	public ResponseEntity<AuthDTO> registerAdmin(@Valid @RequestBody AuthDTO authDTO){
		return new ResponseEntity<>(authService.addAdminAuth(authDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO){
		
		JwtAuthResponse jwtAuthResponse = authService.login(loginDTO);
		
		return new ResponseEntity<>(jwtAuthResponse,HttpStatus.ACCEPTED);
	}

}
