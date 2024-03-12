package com.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	private Integer addressId;
	
	private String doorNo;
	private String area;
	private String city;
	private String state;
	private int pincode;
	
    Merchant merchant;
	
}
