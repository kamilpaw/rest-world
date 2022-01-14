package com.kpaw.world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CityErrorResponse> handleException(CityNotFoundException exc){
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.NOT_EXTENDED.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<CityErrorResponse> MessageNotReadeable(HttpMessageNotReadableException exc){
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.NOT_IMPLEMENTED.value(),
				"Invalid field values",
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CityErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.NOT_EXTENDED.value(),
				fieldErrors.toString(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler
	public ResponseEntity<CityErrorResponse> handleException(Exception exc){
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.NOT_ACCEPTABLE.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}

}
