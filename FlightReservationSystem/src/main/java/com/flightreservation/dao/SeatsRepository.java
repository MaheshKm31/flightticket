package com.flightreservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightreservation.entity.Seats;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Integer> {

	Seats findBySeatId(int seatId);
}
