package com.flightreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fightreservation.service.FlightService;
import com.fightreservation.service.SeatService;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Seats;

@Controller
@RequestMapping(value = "/flight")
public class FlightController {

	@Autowired
	private FlightService flightservice;

	@Autowired
	private SeatService seatService;

	@PostMapping("/add")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {

		Flight message = flightservice.addFlights(flight);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/getAllFlights")
	public ResponseEntity<List<Flight>> getAllFlights() {

		List<Flight> flights = flightservice.getAllFlights();
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}
	@GetMapping("/getFlightsByName")
	public ResponseEntity<List<Flight>> getFlightsByName(@RequestParam("flightName") String flightName) {

		List<Flight> flights = flightservice.getFlights(flightName);
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@GetMapping("/getFlightsBySourceAndDestination")
	public ResponseEntity<List<Flight>> getFlightsBySourceAndDestination(@RequestParam("source") String source,
			@RequestParam("destination") String destination) {

		List<Flight> flights = flightservice.getFlightsbySourceandDestination(source, destination);
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}
	
	@GetMapping("/getFlightsbyFlightIdAndSourceAndDestination")
	public ResponseEntity<Flight> getFlightsbyFlightIdAndSourceAndDestination(@RequestParam("flightId") long flightId, @RequestParam("source") String source,
			@RequestParam("destination") String destination) {

		Flight flight = flightservice.getFlightsbyFlightIdAndSourceAndDestination(flightId, source, destination);
		return new ResponseEntity<>(flight, HttpStatus.OK);
	}
	@PostMapping("/addSeat")
	public ResponseEntity<String> addSeat(@RequestBody Seats seat) {

		String message = seatService.addSeat(seat);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/deleteSeat")
	public ResponseEntity<String> deleteSeat(@RequestParam("flightId") int flightId,
			@RequestParam("seatId") int seatId) {

		String message = seatService.deleteseat(flightId, seatId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/getSeatsById/{flightId}/{seatId}")
	public ResponseEntity<Seats> getSeatsById(@PathVariable("flightId") int flightId,
			@PathVariable("seatId") int seatId) {

		Seats seat = seatService.getSeatById(flightId, seatId);
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	@GetMapping("/getAllSeats/{flightId}")
	public ResponseEntity<List<Seats>> getAllSeats(@PathVariable("flightId") int flightId) {

		List<Seats> seats = seatService.getAllSeats(flightId);
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}
}
