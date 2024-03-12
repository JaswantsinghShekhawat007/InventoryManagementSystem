package com.ms.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String tag;
	
	private Double price;
	
	private int discount;
	
	private int quantity;
	
	private List<String> urls;
	
	private String merchantId;	
	
}
