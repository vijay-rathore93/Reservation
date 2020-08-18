package com.bookingservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

	Boolean existsBySeatNumber(String seatNumber);

	Optional<Seat> findBySeatNumber(String seatNumber);
	
	
	Optional<Seat> findBySeatNumberAndIsFemaleReservedAndIsOccupied(String seatNumber, Boolean isFemaleReserved, Boolean isOccupied);
	
	
	List<Seat> findAllByIsOccupied(Boolean isOccupied);

}
