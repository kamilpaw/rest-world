package com.kpaw.world.controller;

public class CityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityNotFoundException() {
	}

	public CityNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CityNotFoundException(String message) {
		super(message);
	}

	public CityNotFoundException(Throwable cause) {
		super(cause);
	}

	
	
}
