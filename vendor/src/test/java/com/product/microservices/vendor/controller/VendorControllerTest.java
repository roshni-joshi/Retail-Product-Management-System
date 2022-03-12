package com.product.microservices.vendor.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.product.microservices.vendor.model.Vendor;
import com.product.microservices.vendor.service.VendorService;

class VendorControllerTest {

	/*@Autowired 
	VendorService vendorService;*/
	
	//GsonBuilder builder = new GsonBuilder(); 
	//Gson gson = builder.create();
	
	
	
	@Test
	void testVendor() throws Exception{
		Long id=(long) 001;
		Long qty=(long) 10;
		String token="abc";
		Vendor v=new Vendor((long)1,"ABCD",500,(float)3.5);
		/* String jsonStringActual = "{\"vendorId\":101,"
		            + "\"vendorName\":\"ABC\","
		            + "\"vendorCharge\":\"500\","
		            + "\"rating\":\"3.5\"}";
		 Vendor vActual=gson.fromJson(jsonStringActual,Vendor.class);*/
		/*
		 * VendorRepository vendorRepository = Mockito.mock(VendorRepository.class);
		 * VendorStockRepository vendorStock=Mockito.mock(VendorStockRepository.class);
		 */
		
		VendorService vendorService = Mockito.mock(VendorService.class);
		VendorController vendorController=new VendorController(vendorService);
		Mockito.when(vendorService.getVendorDetails(id, qty)).thenReturn(v);
		
		Vendor returnedVendor = vendorController.getVendorDetails(token,id, qty);
		
		assertTrue(v.equals(returnedVendor));
		
		
		//fail("Not yet implemented");
	}

}
