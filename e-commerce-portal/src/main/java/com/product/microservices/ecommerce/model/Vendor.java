package com.product.microservices.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
	
	private Long vendorId;
	private String vendorName;	
	private float deliveryCharge;
}
