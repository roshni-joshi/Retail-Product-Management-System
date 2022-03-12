package com.product.microservices.vendor.service;

import org.springframework.stereotype.Service;

import com.product.microservices.vendor.model.Vendor;
import com.product.microservices.vendor.model.VendorStock;
import com.product.microservices.vendor.repository.VendorRepository;
import com.product.microservices.vendor.repository.VendorStockRepository;

@Service
public class VendorService {
	
	private VendorRepository vendorRepository;
	
	public VendorService(VendorRepository vendorRepository, VendorStockRepository vendorStockRepository) {
		this.vendorRepository = vendorRepository;
	}
	
	public Vendor getVendorDetails(final Long productId, final Long quantity) {
		
		Vendor resultVendor = new Vendor();
		
		if(productId != null) {
			
			Vendor vendor=vendorRepository.findVendor(productId,quantity);
			if(vendor != null)
			{
				resultVendor = vendor;
			}
		}
		return resultVendor;
	}
}
