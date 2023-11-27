package com.flightreservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightreservation.entity.Flight;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	Flight findByFlightId(long flightId);
	List<Flight> findByFlightName(String flightName);
    List<Flight> findBySourceAndDestination(String source, String destination);
    Flight findByFlightIdAndSourceAndDestination(long flightId,String source, String destination);

}
