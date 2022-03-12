package com.product.microservices.vendor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorTest {

	@Test
	void getVendorIdTest( ) {
		Vendor vendor = new Vendor();
		Long VENDOR_ID = (long)10;
		assertNull(vendor.getVendorId());
		vendor.setVendorId(VENDOR_ID);
		assertEquals(VENDOR_ID,vendor.getVendorId());
	}
	
	@Test
	void setVendorIdTest( ) {
		Vendor vendor = new Vendor();
		Long VENDOR_ID = (long)10;
	
		vendor.setVendorId(VENDOR_ID);
		assertEquals(VENDOR_ID,vendor.getVendorId());
	}
	
	@Test
	void getVendorNameTest( ) {
		Vendor vendor = new Vendor();
		String VENDOR_NAME = "ABC";
		assertNull(vendor.getVendorName());
		vendor.setVendorName(VENDOR_NAME);
		assertEquals(VENDOR_NAME,vendor.getVendorName());
	}
	
	@Test
	void setVendorNameTest( ) {
		Vendor vendor = new Vendor();
		String VENDOR_NAME = "ABC";
		vendor.setVendorName(VENDOR_NAME);
		assertEquals(VENDOR_NAME,vendor.getVendorName());
	}
	
	@Test
	void getDeliveryChargeTest( ) {
		Vendor vendor = new Vendor();
		float vendorCharge=(float)10.0;
		//assertNull(vendor.getDeliveryCharge());
		vendor.setDeliveryCharge(vendorCharge);
		assertEquals(vendorCharge,vendor.getDeliveryCharge());
	}
	
	@Test
	void setDeliveryChargeTest( ) {
		Vendor vendor = new Vendor();
		float vendorCharge=10;
		vendor.setDeliveryCharge(vendorCharge);
		assertEquals(vendorCharge,vendor.getDeliveryCharge());
	}
	
	@Test
	void getRatingTest( ) {
		Vendor vendor = new Vendor();
		float vendorRating=(float)2.5;
		//assertNull(vendor.getRating());
		vendor.setRating(vendorRating);
		assertEquals(vendorRating,vendor.getRating());
	}
	
	@Test
	void setRatingTest( ) {
		Vendor vendor = new Vendor();
		float vendorRating=(float)2.5;
		vendor.setRating(vendorRating);
		assertEquals(vendorRating,vendor.getRating());
	}
}
