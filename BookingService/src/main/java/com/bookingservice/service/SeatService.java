package com.bookingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.BusRepo;
import com.bookingservice.SeatRepo;
import com.bookingservice.entity.Bus;
import com.bookingservice.entity.Seat;
import com.bookingservice.exception.BookingException;
import com.bookingservice.exception.NoSeatFoundException;
import com.bookingservice.model.SeatDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatService {

	private final SeatRepo seatRepo;
	private final BusRepo busRepo;
	private final ModelMapper modelMapper;

	public void updateSeats(String busNumber) {
		Bus bus = busRepo.findByBusNumber(busNumber).orElseThrow(() -> new BookingException("Bus not found"));

		List<Seat> seatList = bus.getSeats().stream().map(seat -> {

			seat.setIsOccupied(false);

			return seat;

		}).collect(Collectors.toList());

		seatRepo.saveAll(seatList);
	}

	public List<SeatDTO> displaySeats() {

		List<Seat> seatList = seatRepo.findAll();

		List<SeatDTO> seatDTOList = new ArrayList<SeatDTO>();

		for (Seat seat : seatList) {
			seatDTOList.add(modelMapper.map(seat, SeatDTO.class));
		}

		return seatDTOList;

	}

	public SeatDTO displaySeat(String seatNumber) {
		
		Seat seat=seatRepo.findByActualSeatNumber(seatNumber).orElseThrow(() -> new NoSeatFoundException("Seat not found"));
		
		
		return modelMapper.map(seat, SeatDTO.class);
	}

}
