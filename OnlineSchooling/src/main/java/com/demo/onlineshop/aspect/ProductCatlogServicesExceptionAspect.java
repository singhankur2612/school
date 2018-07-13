package com.demo.onlineshop.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.demo.onlineshop.customresponse.CustomResponse;
import com.demo.onlineshop.exceptions.ProductDetailsNotFoundException;

@ControllerAdvice(basePackages={"com.cg.onlineshop.controllers"})
public class ProductCatlogServicesExceptionAspect {
	@ExceptionHandler(ProductDetailsNotFoundException.class)
	public ResponseEntity<CustomResponse> handelProductDetailsNotFoundException(Exception e){
		CustomResponse response = new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);			
	}
}
