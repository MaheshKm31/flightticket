package com.fightreservation.service;

import java.util.List;

import com.flightreservation.entity.Flight;

public interface FlightService {

	public Flight addFlights(Flight flight);
	public List<Flight> getFlights(String flightname);
	public String deleteFlight(int flightId);
	public Flight editFlight(Flight flight);
	public List<Flight> getFlightsbySourceandDestination(String source, String destination);
	public Flight getFlightsbyFlightIdAndSourceAndDestination(long id, String source, String destination);
	public List<Flight> getAllFlights();
}
