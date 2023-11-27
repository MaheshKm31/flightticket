package com.flightreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fightreservation.service.BookingService;
import com.flightreservation.entity.Bookings;
import com.flightreservation.model.BookingsDTO;

@Controller
@RequestMapping(value = "/book")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/bookseat")
	public ResponseEntity<Bookings> bookSeat(@RequestBody BookingsDTO bookings) {

		Bookings booking = bookingService.bookSeat(bookings);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@GetMapping("/viewBookings")
	public ResponseEntity<Bookings> viewBookings(@RequestParam("bookingId") int bookingId) {

		Bookings booking = bookingService.viewBookings(bookingId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	@DeleteMapping("/cancelBooking")
	public ResponseEntity<String> cancelBookings(@RequestParam("bookingId") int bookingId,
			@RequestParam("seatIds") List<Integer> seatIds){
		String message = bookingService.deleteBooking(bookingId, seatIds);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
