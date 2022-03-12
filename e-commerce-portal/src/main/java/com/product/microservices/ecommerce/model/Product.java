package com.product.microservices.ecommerce.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {

	private Long productId;
	private double price;
	private String name;
	private String description;
	private String imageName;
	private float rating;
	
}
