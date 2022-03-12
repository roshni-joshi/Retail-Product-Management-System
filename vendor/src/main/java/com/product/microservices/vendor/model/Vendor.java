package com.product.microservices.vendor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
	
	@Id
	private Long vendorId;
	private String vendorName;
	private float deliveryCharge;
	private float rating;
}
