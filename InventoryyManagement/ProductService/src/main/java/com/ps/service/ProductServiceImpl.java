package com.ps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.dto.ProductDTO;
import com.ps.entity.Product;
import com.ps.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) {
		
		productDTO.setId( productDTO.getMerchantId()+productDTO.getName() );
		
		if(productRepository.existsById(productDTO.getId())){
			
			productDTO.setQuantity(productRepository.findById(productDTO.getId()).get().getQuantity() + productDTO.getQuantity());
			
			updateProduct(productDTO.getId(), productDTO);
			
			return productDTO;
		}
		
		Product product = new Product();
		
		BeanUtils.copyProperties(productDTO, product);
		
		productRepository.save(product);
				
		return productDTO;
	}

	@Override
	public ProductDTO getProductById(String id) {
		if(!productRepository.existsById(id)) {
			
			throw new RuntimeException
			
			
			("Product with " + id + " Not Found");
		}
			
		ProductDTO productDTO = new ProductDTO();
		
		Product product = productRepository.findById(id).get();
		
		BeanUtils.copyProperties(product, productDTO);
		
		productRepository.delete(product);
	
		return productDTO;
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<Product> products = productRepository.findAll();
		
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product product : products) {
			
			ProductDTO productDTO = new ProductDTO();
			
			BeanUtils.copyProperties(product, productDTO);
			
			productDTOs.add(productDTO);
			
		}
		
		return productDTOs;
	}

	@Override
	public ProductDTO updateProduct(String id, ProductDTO productDTO) {
		
		Product product = productRepository.findById(id).orElseThrow(() ->
		new RuntimeException("Product with " + id + " Not Found"));
		
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setDiscount(productDTO.getDiscount());
		product.setQuantity(productDTO.getQuantity());
		product.setTag(productDTO.getTag());
		product.setUrls(productDTO.getUrls());
		
		productRepository.save(product);
		
		return productDTO;
	}

	@Override
	public ProductDTO deleteProduct(String id) {
		ProductDTO productDTO = new ProductDTO();
		
		Product product = productRepository.findById(id).orElseThrow(() ->
		new RuntimeException("Product with " + id + " Not Found"));
		
		BeanUtils.copyProperties(product, productDTO);
		
		productRepository.delete(product);
	
		return productDTO;
	}

	@Override
	public List<Product> getProductsOfUser(String merchantId) {
		
		List<Product> products = productRepository.findByMerchantId(merchantId);
		
		return products;
	}

	
	

}
