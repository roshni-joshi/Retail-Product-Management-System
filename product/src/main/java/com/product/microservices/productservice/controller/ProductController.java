package com.product.microservices.productservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.product.microservices.productservice.model.Product;
import com.product.microservices.productservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/searchProductById/{productId}")
	public Product searchProductById(@RequestHeader("Authorization") String token, @PathVariable("productId") Long productId) {
		log.debug("Inside searchProductById method");
		return productService.getProductById(productId);
	}

	@GetMapping("/searchProductByName/{name}")
	public Product searchProductByName(@RequestHeader("Authorization") String token, @PathVariable("name") String name, HttpServletRequest request) {
		log.debug("Inside serachProductByName method");
		return productService.getProductByName(name);
	}

	@PostMapping("/addProductRating/{productId}&{rating}")
	public boolean addProductRating(@RequestHeader("Authorization") String token, @PathVariable Long productId, @PathVariable float rating) {
		log.debug("Inside addProductRating method");
		return productService.addRating(productId, rating);
	}

}
