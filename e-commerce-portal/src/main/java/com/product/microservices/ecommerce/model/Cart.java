package com.product.microservices.ecommerce.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	private Long cartId;
	private Long productId;
	private int zipcode;
	private Date deliveryDate;	
	private Vendor vendor;
}
