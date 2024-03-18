package com.auth.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth.pojo.Admin;

@Service
@FeignClient(url = "http://localhost:8088/api/admin", value = "Admin-Client")
public interface AdminClient {
	
	
	@PostMapping("/register")
	public Admin registerAdmin(Admin admin);
	
	@DeleteMapping("/delete/{id}")
	public String deleteAdmin(String adminId);
	
}
