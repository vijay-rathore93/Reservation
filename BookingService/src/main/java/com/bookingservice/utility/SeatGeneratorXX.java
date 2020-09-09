package com.bookingservice.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bookingservice.entity.Passenger;
import com.bookingservice.entity.Seat;
import com.bookingservice.exception.NoSeatFoundException;
import com.bookingservice.repo.BusRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeatGeneratorXX {

	private final BusRepo busRepo;

	List<Seat> maleList = new ArrayList<Seat>();
	List<Seat> femaleList = new ArrayList<Seat>();

	private void seatFiltering(String busNumber) {
		List<Seat> seatList = busRepo.findByBusNumber(busNumber).get().getSeats();

		maleList = seatList.stream().filter(seat -> !seat.getIsFemaleReserved() && !seat.getIsOccupied())
				.collect(Collectors.toList());
		femaleList = seatList.stream().filter(seat -> seat.getIsFemaleReserved() && !seat.getIsOccupied())
				.collect(Collectors.toList());

		if (femaleList.size() == 0 && maleList.size() == 0) {
			throw new NoSeatFoundException("No seats available!");
		}
	}

	public List<Passenger> seatGeneration(List<Passenger> plistx, String busNumber) {

		this.seatFiltering(busNumber);

		for (Passenger p : plistx) {
			if (p.getGender().equalsIgnoreCase("MALE")) {

				p.setSeatNumber(maleSeatFinder(p.getSeatNumber()));
			} else {

				p.setSeatNumber(femaleSeatFinder(p.getSeatNumber()));
			}
		}

		return plistx;
	}

	private String femaleSeatFinder( String seatNumber) {
		
		Seat sn=null;
		
		for(Seat s: femaleList)
		{
			if(seatNumber.equalsIgnoreCase(s.getSeatNumber()))
			{
				femaleList.remove(s);
				return s.getSeatNumber();
			}
		}
		
		for(Seat s: femaleList)
		{
			if(seatNumber.charAt(0)==s.getSeatNumber().charAt(0))
			{
				femaleList.remove(s);
				return s.getSeatNumber();
			}
			else
			{
				sn=s;
			}
		}
		
		if(sn==null)
		{
			return maleSeatFinder(seatNumber);
		}
		
		
		femaleList.remove(sn);

		return sn.getSeatNumber();
		
		
	}

	private String maleSeatFinder(String seatNumber) {

		Seat sn = null;

		for (Seat s : maleList) {
			if (seatNumber.equalsIgnoreCase(s.getSeatNumber())) {
				maleList.remove(s);
				return s.getSeatNumber();
			}

		}

		for (Seat s : maleList) {
			if (seatNumber.charAt(0) == s.getSeatNumber().charAt(0)) {
				maleList.remove(s);
				return s.getSeatNumber();
			} else {
				sn = s;
			}

		}

		maleList.remove(sn);

		return sn.getSeatNumber();
	}

}