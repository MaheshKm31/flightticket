package com.flightreservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fightreservation.service.FlightService;
import com.flightreservation.dao.FlightRepository;
import com.flightreservation.entity.Flight;
import com.flightreservation.exceptions.FlightApplicationException;
import com.flightreservation.util.ErrorStatus;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightrepo;

	public Flight addFlights(Flight flight) {
		Flight flightObj = null;
		try {
			flightObj = flightrepo.save(flight);
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.FLIGHT_CREATION.getCode(),
					ErrorStatus.FLIGHT_CREATION.getDescription());
		}
		return flightObj;
	}

	@Override
	public List<Flight> getFlights(String flightname) {
		List<Flight> flights = null;
		try {
			flights = flightrepo.findByFlightName(flightname);
			if (CollectionUtils.isEmpty(flights))
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return flights;
	}

	public String deleteFlight(int flightId) {
		try {
			Flight flight = flightrepo.findByFlightId(flightId);
			flightrepo.delete(flight);
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return "Deleted Successfully";
	}

	public Flight editFlight(Flight flight) {
		Flight flightObj = null;
		try {
			flightObj = flightrepo.save(flight);
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return flightObj;
	}

	@Override
	public List<Flight> getFlightsbySourceandDestination(String source, String destination) {
		List<Flight> flights = null;
		try {
			flights = flightrepo.findBySourceAndDestination(source, destination);
			if (CollectionUtils.isEmpty(flights))
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return flights;
	}

	@Override
	public Flight getFlightsbyFlightIdAndSourceAndDestination(long id, String source, String destination) {
		Flight flight = null;
		try {
			flight = flightrepo.findByFlightIdAndSourceAndDestination(id, source, destination);
			if (flight == null)
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return flight;
	}

	public List<Flight> getAllFlights() {
		List<Flight> flights = null;
		try {
			flights = flightrepo.findAll();
			if (CollectionUtils.isEmpty(flights))
				throw new FlightApplicationException(ErrorStatus.FLIGHT_NOT_FOUND.getCode(),
						ErrorStatus.FLIGHT_NOT_FOUND.getDescription());
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return flights;
	}
}
