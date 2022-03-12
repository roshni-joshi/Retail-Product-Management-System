package com.product.microservices.productservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.microservices.productservice.model.Product;
import com.product.microservices.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Product getProductById(final Long id) {

		Product product=new Product();
		if(id!=null)
		{	
		    Optional<Product> optionalProduct = productRepository.findById(id);
		    if(optionalProduct!=null && optionalProduct.isPresent())
		    {
		    	product=optionalProduct.get();
		    }
		}
		return product;
	}
	
	public Product getProductByName(final String name) {
		
		Product product=new Product();
		
		if(name!=null)
		{
			Product savedProduct=productRepository.findByName(name);
			if(savedProduct != null)
			{
				product = savedProduct;
			}
		}
		
		return product;
	}
	
	public boolean addRating(final Long productId, final float rating) {

		Product product=getProductById(productId);
		product.setRating(rating);
		Product savedProduct=productRepository.save(product);
		if(savedProduct!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
