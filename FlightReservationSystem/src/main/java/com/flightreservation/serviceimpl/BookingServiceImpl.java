package com.flightreservation.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fightreservation.service.BookingService;
import com.flightreservation.dao.BookingRepository;
import com.flightreservation.dao.FlightRepository;
import com.flightreservation.dao.UserRepository;
import com.flightreservation.entity.Bookings;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Seats;
import com.flightreservation.entity.User;
import com.flightreservation.exceptions.FlightApplicationException;
import com.flightreservation.model.BookingsDTO;
import com.flightreservation.util.ErrorStatus;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	private UserRepository userRepo;
	
	public Bookings bookSeat(BookingsDTO booking) {
		Bookings bookingObj = null;
		try {
			User user = userRepo.findByusername(booking.getUsername());
			if(user==null)
				throw new FlightApplicationException(ErrorStatus.USER_NOT_FOUND.getCode(),
						ErrorStatus.USER_NOT_FOUND.getDescription());
			Flight flight = flightRepo.findByFlightIdAndSourceAndDestination(booking.getFlightId(),booking.getSource(),booking.getDestination());
			if (flight == null)
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
			List<Integer> toBookIds = booking.getSeatIds();
			List<Seats> bookedSeats = new ArrayList<>();
			if (CollectionUtils.isEmpty(flight.getSeats()))
				throw new FlightApplicationException(ErrorStatus.SEAT_NOT_FOUND.getCode(),
						ErrorStatus.SEAT_NOT_FOUND.getDescription());
			flight.getSeats().forEach(seat -> {
				for (Integer seatId : toBookIds) {
					if (seat.getSeatId() == seatId.intValue()) {
						seat.setBooked(true);
						seat.setUserId(user.getId());
						bookedSeats.add(seat);
					}
				}
			});
			flightRepo.save(flight);
			bookingObj = new Bookings();
			bookingObj.setBookingId(booking.getFlightId());
			bookingObj.setUserId(user.getId());
			bookingObj.setSeats(bookedSeats);
			bookingObj.setFlightId(booking.getFlightId());
			bookingRepo.save(bookingObj);
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return bookingObj;
	}

	public String deleteBooking(int bookingId, List<Integer> seatIds) {
		try {
			Bookings booking = bookingRepo.findByBookingId(bookingId);
			if (booking == null)
				throw new FlightApplicationException(ErrorStatus.BOOKINGS_NOT_FOUND.getCode(),
						ErrorStatus.BOOKINGS_NOT_FOUND.getDescription());
			Flight flight = flightRepo.findByFlightId(booking.getFlightId());
			if (flight == null)
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
			List<Seats> bookedSeats = new ArrayList<>();
			flight.getSeats().forEach(seat -> {
				for (Integer bookedSeatId : seatIds) {
					if (seat.getSeatId() == bookedSeatId)
						seat.setBooked(false);
					seat.setUserId(0);
					bookedSeats.add(seat);
				}
			});
			flightRepo.save(flight);
			bookingRepo.delete(booking);
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return "success";
	}

	public Bookings viewBookings(int bookingId) {
		Bookings booking = null;
		try {
			booking = bookingRepo.findByBookingId(bookingId);
			if (booking == null)
				throw new FlightApplicationException(ErrorStatus.BOOKINGS_NOT_FOUND.getCode(),
						ErrorStatus.BOOKINGS_NOT_FOUND.getDescription());
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return booking;
	}
}
