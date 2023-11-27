package com.flightreservation.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fightreservation.service.SeatService;
import com.flightreservation.dao.FlightRepository;
import com.flightreservation.dao.SeatsRepository;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Seats;
import com.flightreservation.exceptions.FlightApplicationException;
import com.flightreservation.util.ErrorStatus;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatsRepository seatrepo;

	@Autowired
	private FlightRepository flightRepo;

	@Override
	public String addSeat(Seats seat) {
		try {
			Flight flight = flightRepo.findByFlightId(seat.getFlightId());
			if (flight == null) {
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
			}
			Seats seatObj = seatrepo.save(seat);
			flight.getSeats().add(seatObj);
			flightRepo.save(flight);
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return "Success";
	}

	@Override
	public String deleteseat(int flightId, int seatId) {
		try {
			Flight flight = flightRepo.findByFlightId(flightId);
			if (flight == null)
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
			Seats seatObj = seatrepo.findBySeatId(seatId);
			if (seatObj == null)
				throw new FlightApplicationException(ErrorStatus.SEAT_NOT_FOUND.getCode(),
						ErrorStatus.SEAT_NOT_FOUND.getDescription());
			flight.getSeats().remove(seatObj);
			flightRepo.save(flight);
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return "Success";
	}

	@Override
	public Seats getSeatById(int flightId, int seatId) {
		Seats seats = null;
		try {
			Flight flight = flightRepo.findByFlightId(flightId);
			if (flight == null)
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
			for (Seats seat : flight.getSeats()) {
				if (seat.getSeatId() == seatId)
					return seat;
			}
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return seats;
	}

	public List<Seats> getAllSeats(int flightid) {
		List<Seats> seats = null;
		try {
			Flight flight = flightRepo.findByFlightId(flightid);
			if (flight == null)
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
			seats = flight.getSeats();
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return seats;
	}
}
