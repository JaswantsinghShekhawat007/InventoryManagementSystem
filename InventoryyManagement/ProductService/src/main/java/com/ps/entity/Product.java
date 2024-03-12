package com.ps.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
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
