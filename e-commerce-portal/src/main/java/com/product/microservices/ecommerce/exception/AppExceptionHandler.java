package com.product.microservices.ecommerce.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherExceptions(Exception exception, WebRequest request)
	{
		log.debug("Exception handled : " + exception.getMessage());
		return new ResponseEntity<Object>("Error occured while accessing the resource. Please contact support service at 111-111.", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
