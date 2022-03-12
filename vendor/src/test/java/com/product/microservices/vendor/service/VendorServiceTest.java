package com.product.microservices.vendor.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.product.microservices.vendor.model.Vendor;
import com.product.microservices.vendor.repository.VendorRepository;
import com.product.microservices.vendor.repository.VendorStockRepository;

class VendorServiceTest {

	@Test
	void getVendorDetailsTest() throws Exception{
		Long id=(long) 001;
		Long qty=(long) 10;
		Vendor v=new Vendor((long)1,"ABCD",500,(float)3.5);
		/* String jsonStringActual = "{\"vendorId\":101,"
		            + "\"vendorName\":\"ABC\","
		            + "\"vendorCharge\":\"500\","
		            + "\"rating\":\"3.5\"}";
		 Vendor vActual=gson.fromJson(jsonStringActual,Vendor.class);*/
		VendorRepository vendorRepository = Mockito.mock(VendorRepository.class);
		VendorStockRepository vendorStock=Mockito.mock(VendorStockRepository.class);
	
		VendorService vendorService = new VendorService(vendorRepository,vendorStock);
		
		Mockito.when(vendorRepository.findVendor(id, qty)).thenReturn(v);
		
		Vendor returnedVendor = vendorService.getVendorDetails(id, qty);
		
		assertTrue(v.equals(returnedVendor));
		
		
		//fail("Not yet implemented");
	}
}
