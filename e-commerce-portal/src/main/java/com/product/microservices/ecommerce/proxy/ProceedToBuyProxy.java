package com.product.microservices.ecommerce.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.product.microservices.ecommerce.model.Cart;
import com.product.microservices.ecommerce.model.Wishlist;

@FeignClient(name = "proceed-to-buy", url = "localhost:8765/proceed-to-buy")
//@FeignClient(name = "proceed-to-buy")
public interface ProceedToBuyProxy {

	@PostMapping("/addProductToCart/{quantity}")
	public Cart addProductToCart(@RequestHeader("Authorization") String token, @RequestBody final Cart cart, @PathVariable final Long quantity);
	
	@PostMapping("/addProductToWishlist")
	public boolean addProductToWishlist(@RequestHeader("Authorization") String token, @RequestBody Wishlist wishlist);
	
	@PostMapping("/getAllProducts/{username}")
	public List<Wishlist> getAllProducts(@RequestHeader("Authorization") String token, @PathVariable String username);
}
