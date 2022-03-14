package com.product.microservices.proceedtobuy.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.product.microservices.proceedtobuy.model.Vendor;

@FeignClient(name = "vendor", url = "${api.gateway.url}/vendor")
//@FeignClient(name = "vendor")
public interface VendorProxy {
	
	@GetMapping(path = "/getVendorDetails/{productId}&{quantity}")
	public Vendor getVendorDetails(@RequestHeader("Authorization") String token ,@PathVariable final Long productId,@PathVariable final Long quantity); 

}
