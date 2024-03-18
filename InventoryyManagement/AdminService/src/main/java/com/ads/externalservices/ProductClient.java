package com.ads.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ads.pojo.Product;

@Service
@FeignClient(url = "http://localhost:8090/api/product", value = "Product-Client")
public interface ProductClient {

	@GetMapping("/getAll")
	public List<Product> getAllProducts();
}
