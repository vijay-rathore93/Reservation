package com.bookingservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookingservice.BusRepo;
import com.bookingservice.SeatRepo;
import com.bookingservice.entity.Booking;
import com.bookingservice.entity.Bus;
import com.bookingservice.entity.Seat;
import com.bookingservice.exception.BookingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatService {
	
	private final SeatRepo seatRepo;
	private final BusRepo busRepo;
	
	public void updateSeats(String busNumber)
	{
		Bus bus=busRepo.findByBusNumber(busNumber).orElseThrow(()->new BookingException("Bus Not found"));
		//Bus bus=busRepo.findByBusNumber(busNumber).get();
		List<Seat> seatList=bus.getSeats();
		
		for(Seat s:seatList)
		{
			s.setIsOccupied(false);
		}
		
		bus.setSeats(seatList);
		
		busRepo.save(bus);
		
		seatRepo.saveAll(seatList);
	}

	public List<Seat> displaySeats() {
	
		
		return seatRepo.findAll();
		
	}

	
	
	

}
