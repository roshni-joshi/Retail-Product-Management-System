package com.product.microservices.vendor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class VendorStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vendorStockId;
	private Long productId;
	private Long vendorId;
	private Long stockInHand;
	private Date stockRepDate;	
}
