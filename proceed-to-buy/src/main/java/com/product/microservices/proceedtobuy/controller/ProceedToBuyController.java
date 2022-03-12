package com.product.microservices.proceedtobuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.product.microservices.proceedtobuy.model.Cart;
import com.product.microservices.proceedtobuy.model.Vendor;
import com.product.microservices.proceedtobuy.model.Wishlist;
import com.product.microservices.proceedtobuy.proxy.VendorProxy;
import com.product.microservices.proceedtobuy.service.ProceedToBuyService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProceedToBuyController {

	@Autowired
	private ProceedToBuyService proceedToBuyService;
	
	@Autowired
	private VendorProxy vendorProxy;
	
	@Retry(name = "default", fallbackMethod = "defaultVendor")
	@PostMapping("/addProductToCart/{quantity}")
	public Cart addProductToCart(@RequestHeader("Authorization") String token, @RequestBody final Cart cart, @PathVariable final Long quantity)
	{
		log.debug("Inside addProductToCart method");
		Cart resultCart = cart;
		
		Vendor vendor = vendorProxy.getVendorDetails(token, cart.getProductId(), quantity);
		
		if(vendor.getVendorId() != 0)
		{
			Vendor savedVendor = proceedToBuyService.addVendor(vendor);
			if(savedVendor != null)
			{
				resultCart.setVendor(savedVendor);
				resultCart = proceedToBuyService.addCart(resultCart);
			}
			else
			{
				resultCart.setVendor(new Vendor(0l, null, 0f));	
			}
		}
		else
		{
			resultCart.setVendor(new Vendor(0l, null, 0f));
		}
		
		return resultCart;
	}
	
	@PostMapping("/addProductToWishlist")
	public boolean addProductToWishlist(@RequestHeader("Authorization") String token, @RequestBody final Wishlist wishlist)
	{
		log.debug("Inside addProductToWishlist method");
		
		Wishlist resultWishlist = proceedToBuyService.addWishlist(wishlist);
		
		if(resultWishlist != null && resultWishlist.getWishlistId() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@PostMapping("/getAllProducts/{username}")
	public List<Wishlist> getAllProducts(@RequestHeader("Authorization") String token, @PathVariable String username) {
		
		log.debug("Inside getAllProducts method");
		return proceedToBuyService.getAllProducts(username);
	}
	
	public Cart defaultVendor(Exception ex)
	{
		log.debug("Exception Hndling : Inside defaultVendor method");
		return new Cart();
	}
}
