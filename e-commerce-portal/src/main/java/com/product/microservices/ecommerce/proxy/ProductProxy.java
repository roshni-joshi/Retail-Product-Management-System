package com.product.microservices.ecommerce.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.product.microservices.ecommerce.model.Product;

@FeignClient(name = "product", url = "${api.gateway.url}/product")
//@FeignClient(name = "product")
public interface ProductProxy {
	
	@GetMapping("/searchProductById/{productId}")
	public Product searchProductById(@RequestHeader("Authorization") String token, @PathVariable("productId") Long productId);
	
	@GetMapping("/searchProductByName/{name}")
	public Product searchProductByName(@RequestHeader("Authorization") String token, @PathVariable("name") String name);
		
	@PostMapping("/addProductRating/{productId}&{rating}")
	public boolean addProductRating(@RequestHeader("Authorization") String token, @PathVariable Long productId, @PathVariable float rating);
	
	@GetMapping("/checkHeader")
	public void checkHeader();
	
}
