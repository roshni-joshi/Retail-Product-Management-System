package com.product.microservices.proceedtobuy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.product.microservices.proceedtobuy.model.Cart;
import com.product.microservices.proceedtobuy.model.Product;
import com.product.microservices.proceedtobuy.model.Vendor;
import com.product.microservices.proceedtobuy.model.Wishlist;
import com.product.microservices.proceedtobuy.repository.CartRepository;
import com.product.microservices.proceedtobuy.repository.ProductRepository;
import com.product.microservices.proceedtobuy.repository.VendorRepository;
import com.product.microservices.proceedtobuy.repository.WishlistRepository;

@Service
public class ProceedToBuyService {
	
	private CartRepository cartRepository;
	private VendorRepository vendorRepository;
	private WishlistRepository wishlistRepository;
	private ProductRepository productRepository;

	public ProceedToBuyService(final CartRepository cartRepository, final VendorRepository vendorRepository, final WishlistRepository wishlistRepository, final ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.vendorRepository = vendorRepository;
		this.wishlistRepository = wishlistRepository;
		this.productRepository = productRepository;
	}

	public Cart addCart(final Cart cart)
	{
		Cart savedCart = null;
		
		if(cart != null)
		{
			savedCart = cartRepository.save(cart);
		}		
		return savedCart;
	}
	
	public Vendor addVendor(final Vendor vendor)
	{
		Vendor savedVendor = null;
		
		if(vendor != null)
		{
			savedVendor = vendorRepository.save(vendor);
		}
		
		return savedVendor;
	}
	
	public Wishlist addWishlist(final Wishlist wishlist)
	{
		Wishlist savedWishlist = null;
		Product savedProduct = null;
		
		if(wishlist != null)
		{
			savedProduct = productRepository.save(wishlist.getProduct());
			wishlist.setProduct(savedProduct);
			savedWishlist = wishlistRepository.save(wishlist);
		}
		
		return savedWishlist;
	}
	
	public List<Wishlist> getAllProducts(final String username)
	{
		List<Wishlist> wishlist = new ArrayList<Wishlist>();
		
		if(username != null && !username.isEmpty()) {
			wishlist = wishlistRepository.findAllByUsername(username);
		}
		
		return wishlist;
	}
}
