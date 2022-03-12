package com.product.microservices.vendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.product.microservices.vendor.model.Vendor;
import com.product.microservices.vendor.service.VendorService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@GetMapping(path = "/getVendorDetails/{productId}&{quantity}")
	public Vendor getVendorDetails(@RequestHeader("Authorization") String token, @PathVariable final Long productId,@PathVariable final Long quantity) {
		log.debug("Inside getVendorDetails method");
		
		Vendor vendor =  vendorService.getVendorDetails(Long.valueOf(productId),Long.valueOf(quantity));
		
		if(vendor.getVendorId() == null)
		{
			Vendor defaultVendor = new Vendor();
			defaultVendor.setVendorId(0l);
			return defaultVendor;
		}
		
		return vendor;
	}
}
