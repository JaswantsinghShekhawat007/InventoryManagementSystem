package com.ads.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ads.pojo.MerchantDTO;

@Service
@FeignClient(url = "http://localhost:8092/api/merchant", value = "Merchant-Client")
public interface MerchantClient {
	
	@GetMapping("/get-merchant/all")
	public List<MerchantDTO> getAllMerchant();

}
