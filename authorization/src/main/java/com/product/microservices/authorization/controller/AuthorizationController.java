package com.product.microservices.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.microservices.authorization.model.JwtToken;
import com.product.microservices.authorization.service.MyUserDetailsService;
import com.product.microservices.authorization.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthorizationController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@PostMapping("/generateToken/{username}&{password}")
	public JwtToken generateToken(@PathVariable String username, @PathVariable String password) {
		log.debug("Inside generateToken method");
		JwtToken jwtToken = null;

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			log.info("Inavlid username and password");
			jwtToken = new JwtToken("default");
			return jwtToken;
		} catch(Exception e) {
			log.info("Inavlid username and password");
			jwtToken = new JwtToken("default");
			return jwtToken;
		}
	
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
		final String jwt = jwtUtil.generateToken(userDetails);
		
		log.debug("Token has generated for user : " + username);
		return new JwtToken(jwt);
	}
}
