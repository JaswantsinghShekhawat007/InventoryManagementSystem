package com.auth.services;

import com.auth.dto.AuthDTO;
import com.auth.dto.JwtAuthResponse;
import com.auth.dto.LoginDTO;

public interface AuthService {
	
	AuthDTO addMerchantAuth(AuthDTO authDTO);
	
	AuthDTO addAdminAuth(AuthDTO authDTO);
	
	JwtAuthResponse login(LoginDTO loginDTO);
	
}
