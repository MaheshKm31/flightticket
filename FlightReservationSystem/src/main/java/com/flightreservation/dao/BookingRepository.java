package com.flightreservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightreservation.entity.Bookings;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer> {
	Bookings findByBookingId(long bookingId);
}
