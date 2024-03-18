package com.ps.service;

import java.util.List;

import com.ps.dto.ProductDTO;
import com.ps.entity.Product;

public interface ProductService {
	
	ProductDTO addProduct(ProductDTO productDTO);
	
	ProductDTO getProductById(String id);
	
	List<ProductDTO> getAllProduct();
	
	ProductDTO updateProduct(String id, ProductDTO productDTO);
	
	ProductDTO deleteProduct(String id);
	
	String deleteMerchantProduct(String merchantId);
	
	List<Product> getProductsOfUser(String merchantId);
}
