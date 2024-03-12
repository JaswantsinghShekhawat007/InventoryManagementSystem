package com.auth.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth.entity.Merchant;

@Service
@FeignClient(url = "http://localhost:8092/api/merchant", value = "Merchant-Client")
public interface MerchantClient {
	
	@PostMapping("/register")
	public Merchant registerMerchant(Merchant merchant);
}
