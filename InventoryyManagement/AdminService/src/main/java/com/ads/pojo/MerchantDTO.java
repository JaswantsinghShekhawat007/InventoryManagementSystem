package com.ads.pojo;

import java.util.List;

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
	
	private AddressDTO address;
	
	transient private List<Product> products;

}