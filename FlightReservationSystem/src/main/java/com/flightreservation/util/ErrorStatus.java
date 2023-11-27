package com.flightreservation.util;

public enum ErrorStatus {

	USERNAME_PASSWORD_FORMAT_EXCEPTION(400,"Username & Password cannot contain symbols like [^`~|#{}=+-_]"),
	USER_NOT_FOUND(404,"User does not exist."),
	FLIGHT_CREATION(500,"Error while creating Flight"),
	FLIGHT_NOT_FOUND(404,"Flights Not Found"),
	SEAT_NOT_FOUND(404,"Seat Not Found"),
	BOOKINGS_NOT_FOUND(404,"No bookings found for the booking id"),
	GENERAL_ERROR(500,"Internal Server Error Please Contact Customer Support.");

	private final int code;
	private final String description;

	ErrorStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
