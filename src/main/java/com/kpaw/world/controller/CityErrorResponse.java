package com.kpaw.world.controller;

public class CityErrorResponse {

	private final int status;
	private final String message;
	private final long timeStamp;


	public CityErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
}
