package com.bookingservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

	Boolean existsBySeatNumber(String seatNumber);
	
	Boolean existsByActualSeatNumber(String seatNumber);

	Optional<Seat> findBySeatNumber(String seatNumber);
	
	Optional<Seat> findByActualSeatNumber(String seatNumber);
	
	Boolean existsBySeatNumberAndIsOccupied(String seatNumber, Boolean isOccupied);
	
	Boolean existsByActualSeatNumberAndIsOccupied(String seatNumber, Boolean isOccupied);
	
	
	Optional<Seat> findBySeatNumberAndIsFemaleReservedAndIsOccupied(String seatNumber, Boolean isFemaleReserved, Boolean isOccupied);
	
	
	//Optional<Seat> findByActualSeatNumberAndIsFemaleReservedAndIsOccupied(String seatNumber, Boolean isFemaleReserved, Boolean isOccupied);
	//List<Seat> findByActualSeatNumberAndIsFemaleReservedAndIsOccupied(String seatNumber, Boolean isFemaleReserved, Boolean isOccupied);
	List<Seat> findByActualSeatNumberAndIsFemaleReservedAndIsOccupied(String seatNumber, Boolean isFemaleReserved, Boolean isOccupied);
	List<Seat> findAllByIsOccupied(Boolean isOccupied);

	List<Seat> findByIsFemaleReservedAndIsOccupied(Boolean isFemaleReserved, Boolean isOccupied);
	
	
}
