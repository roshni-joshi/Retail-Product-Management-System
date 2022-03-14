package com.product.microservices.apigatewayauth.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.product.microservices.apigatewayauth.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtRequestFilter implements GlobalFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		
		if(this.isAuthMissing(request))
		{
			log.debug("Header not found");
            return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
		} 

		final String token = this.extractAuthToken(request);
        
		if(jwtUtil.validateToken(token))
        {
			log.debug("Valid token");
        	this.populateRequestWithHeaders(exchange, token);
        }
        else
        {
        	log.debug("Invalid token");
        	return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
        }        
        return chain.filter(exchange);
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.extractAllClaims(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
	
	protected String extractAuthToken(ServerHttpRequest request){

        String jwtToken = null;

        String bearerToken = request.getHeaders().getFirst("Authorization");

        if(bearerToken != null && !bearerToken.isEmpty()){
            String[] parts = bearerToken.split(" ");
            jwtToken = parts[parts.length-1].trim(); 

        }

        if(jwtToken == null){
            jwtToken = request.getQueryParams().getFirst("access_token");

            if(jwtToken == null || jwtToken.isEmpty()) {
                jwtToken = request.getQueryParams().getFirst("jwt");
            }
        }

        return jwtToken;
    }
}
