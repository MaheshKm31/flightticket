package com.fightreservation.service;

import java.util.List;

import com.flightreservation.entity.Seats;

public interface SeatService {

	public String addSeat(Seats seat);
	public String deleteseat(int flightId, int seatId);
	public Seats getSeatById(int flightId, int seatId);
	public List<Seats> getAllSeats(int flightid);
}
