package com.product.microservices.ecommerce.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.product.microservices.ecommerce.model.JwtToken;

import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "authorization", url = "${auth.url}")
//@FeignClient(name = "authorization")
public interface AuthorizationProxy {

	@PostMapping("/generateToken/{username}&{password}")
	public JwtToken generateToken(@PathVariable String username, @PathVariable String password);
	
}
