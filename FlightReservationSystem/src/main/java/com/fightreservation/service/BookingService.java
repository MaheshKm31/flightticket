package com.fightreservation.service;

import java.util.List;
import com.flightreservation.entity.Bookings;
import com.flightreservation.model.BookingsDTO;

public interface BookingService {

	public Bookings bookSeat(BookingsDTO booking);
	public String deleteBooking(int bookingId,List<Integer> seatIds);
	public Bookings viewBookings(int bookingId);
}
