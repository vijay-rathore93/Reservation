package com.bookingservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookingservice.BusRepo;
import com.bookingservice.SeatRepo;
import com.bookingservice.entity.Bus;
import com.bookingservice.entity.Seat;
import com.bookingservice.exception.BookingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatService {

	private final SeatRepo seatRepo;
	private final BusRepo busRepo;

	public void updateSeats(String busNumber) {
		Bus bus = busRepo.findByBusNumber(busNumber).orElseThrow(() -> new BookingException("Bus not found"));

		List<Seat> seatList = bus.getSeats().stream().map(seat -> {

			seat.setIsOccupied(false);

			return seat;

		}).collect(Collectors.toList());

		seatRepo.saveAll(seatList);
	}

	public List<Seat> displaySeats() {

		return seatRepo.findAll();

	}

}
