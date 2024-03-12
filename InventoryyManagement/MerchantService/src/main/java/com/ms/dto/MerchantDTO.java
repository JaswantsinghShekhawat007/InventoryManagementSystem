package com.ms.dto;

import java.util.List;

import com.ms.entity.Address;
import com.ms.entity.Product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDTO {
	
	private String merchantId;
	
	@NotEmpty(message = "Name Field Empty")
	private String name;
	
	@NotEmpty(message = "Please provide contact number")
	@Size(max=10, min=10, message = "Invalid contact number")
	private String contactNo;
	
	private Address address;
	
	transient private List<Product> products;

}