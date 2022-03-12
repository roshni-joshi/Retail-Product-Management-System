package com.product.microservices.proceedtobuy.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendor {
	
	@Id
	private Long vendorId;
	private String vendorName;	
	private float deliveryCharge;
}
