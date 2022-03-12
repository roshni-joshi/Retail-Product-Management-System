package com.product.microservices.vendor.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class VendorStockTest {

	@Test
	void getVendorStockIdTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long VENDOR_ID = (long)10;
		assertNull(vendorStock.getVendorId());
		vendorStock.setVendorStockId(VENDOR_ID);
		assertEquals(VENDOR_ID,vendorStock.getVendorStockId());
	}
	
	@Test
	void setVendorStockIdTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long VENDOR_ID = (long)10;
		vendorStock.setVendorStockId(VENDOR_ID);
		assertEquals(VENDOR_ID,vendorStock.getVendorStockId());
	}
	
	@Test
	void getProductIdTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long PRODUCT_ID = (long)10;
		assertNull(vendorStock.getProductId());
		vendorStock.setProductId(PRODUCT_ID);
		assertEquals(PRODUCT_ID,vendorStock.getProductId());
	}

	@Test
	void setProductIdTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long PRODUCT_ID = (long)10;
		vendorStock.setProductId(PRODUCT_ID);
		assertEquals(PRODUCT_ID,vendorStock.getProductId());
	}
	
	@Test
	void getVendorIdTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long VENDOR_ID = (long)10;
		assertNull(vendorStock.getVendorId());
		vendorStock.setVendorId(VENDOR_ID);
		assertEquals(VENDOR_ID,vendorStock.getVendorId());
	}
	
	@Test
	void setVendorIdTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long VENDOR_ID = (long)10;
		vendorStock.setVendorId(VENDOR_ID);
		assertEquals(VENDOR_ID,vendorStock.getVendorId());
	}
	
	@Test
	void getStockInHandTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long STOCK_IN_HAND = (long)10;
		assertNull(vendorStock.getStockInHand());
		vendorStock.setStockInHand(STOCK_IN_HAND);
		assertEquals(STOCK_IN_HAND,vendorStock.getStockInHand());
	}
	
	@Test
	void setStockInHandTest( ) {
		VendorStock vendorStock = new VendorStock();
		Long STOCK_IN_HAND = (long)10;
		vendorStock.setStockInHand(STOCK_IN_HAND);
		assertEquals(STOCK_IN_HAND,vendorStock.getStockInHand());
	}
	
	@Test
	void getStockRepDateTest( ) {
		VendorStock vendorStock = new VendorStock();
		Date stockDate=new Date();
		assertNull(vendorStock.getStockRepDate());
		vendorStock.setStockRepDate(stockDate);
		assertEquals(stockDate,vendorStock.getStockRepDate());
	}
	
	@Test
	void setStockRepDateTest( ) {
		VendorStock vendorStock = new VendorStock();
		Date stockDate=new Date();
		vendorStock.setStockRepDate(stockDate);
		assertEquals(stockDate,vendorStock.getStockRepDate());
	}
}
