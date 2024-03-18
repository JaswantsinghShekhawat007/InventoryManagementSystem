package com.ms.dto;

import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
		
	@NotEmpty(message = "Empty Field Door Number")
	private String doorNo;
	
	@NotEmpty(message = "Empty Field Area")
	private String area;
	
	@NotEmpty(message = "Empty Field City")
	private String city;
	
	@NotEmpty(message = "Empty Field State")
	private String state;
	
	@NotEmpty
	@Size(max=6, min=6,message = "Invalid Pincode")
	private String pincode;	
}

