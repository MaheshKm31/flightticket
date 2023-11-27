package com.flightreservation.exceptions;

public class FlightApplicationException extends RuntimeException {

	private static final long serialVersionUID = -9121389320307275362L;
	private final int code;
    private final String description;

    public FlightApplicationException(int i, String description) {
        super(description);
        this.code = i;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}