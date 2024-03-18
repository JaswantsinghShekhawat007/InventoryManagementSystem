package com.auth.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//import com.auth.dto.JwtAuthResponse;
import com.auth.pojo.Merchant;

@Service
@FeignClient(url = "http://localhost:8092/api/merchant", value = "Merchant-Client")
public interface MerchantClient {
	
	@PostMapping("/register")
	public Merchant registerMerchant(Merchant merchant);
	
	@DeleteMapping("/delete-merchant/{id}")
	public String deleteMerchant(String id);
}
