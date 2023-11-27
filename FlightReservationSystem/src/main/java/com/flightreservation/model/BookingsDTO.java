package com.flightreservation.model;

import java.util.List;

public class BookingsDTO {
	
	private String username;
	private int flightId;
	private String source;
	private String destination;
	private List<Integer> seatIds;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getFlightId() {
		return flightId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public List<Integer> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}
	
		
}
