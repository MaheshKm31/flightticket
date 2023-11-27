package com.flightreservation.model;

import java.io.Serializable;

public class SeatDTO implements Serializable{

	private static final long serialVersionUID = -8530000517922978153L;

	private long flightId;
	private long seatId;
	private boolean isBooked;
	public long getFlightId() {
		return flightId;
	}
	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}
	public long getSeatId() {
		return seatId;
	}
	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
}
