package com.kpaw.world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CityErrorResponse> handleException(CityNotFoundException exc){
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CityErrorResponse> handleValidationExceptions(
			MethodArgumentNotValidException exc) {
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				exc.getBindingResult().getAllErrors().toString(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<CityErrorResponse> MessageNotReadable(HttpMessageNotReadableException exc){
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"Invalid field values \n" + exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
		return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
	}
	
	@ExceptionHandler
	public ResponseEntity<CityErrorResponse> handleAllException(Exception exc){
		CityErrorResponse error = new CityErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
