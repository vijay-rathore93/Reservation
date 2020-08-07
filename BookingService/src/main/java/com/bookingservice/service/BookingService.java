package com.bookingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.bookingservice.BookingRepo;
import com.bookingservice.entity.Booking;
import com.bookingservice.entity.Itinerary;
import com.bookingservice.entity.Passenger;
import com.bookingservice.exception.NoUserFoundException;
import com.bookingservice.model.BookingDTO;
import com.bookingservice.model.ItineraryDTO;
import com.bookingservice.model.PassengerDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepo bookingRepo;
	private final BookingDTO bookingDTO;
	private final ItineraryDTO itineraryDTO;
	
	public void bookingCreation(Booking booking) {

		booking.setBookingId(RandomStringUtils.random(30, true, true) + booking.getCustomerId());

		bookingRepo.save(booking);

		// BookingId should be generated ALphanumeric 16 digit

		// gender will be one of male,female,other (using enum)

	}

	public List<Booking> getAllBookings() {
		
		return bookingRepo.findAll();
	}

	public BookingDTO getTicket(String bookingId) {
		
		
		Booking booking=bookingRepo.findByBookingId(bookingId).orElseThrow(()->new NoUserFoundException("No Data Found"));
		
		
		
		bookingDTO.setBookingId(booking.getBookingId());
		
		Itinerary itinerary=booking.getItinerary();
		
		itineraryDTO.setArrivalTime(itinerary.getExpectedArrivalTime());
		itineraryDTO.setDepartureTime(itinerary.getTimeOfDeparture());
		itineraryDTO.setTotalJourneyHours(itinerary.getTravelDuration());
		itineraryDTO.setSource(itinerary.getSource());
		itineraryDTO.setDestination(itinerary.getDestination());
		
		bookingDTO.setItineraryDTO(itineraryDTO);
		
		
		List<Passenger> passengerList=booking.getPassengerList();
		
		List<PassengerDTO> passengerDTOList=new ArrayList<PassengerDTO>();
		
		for(Passenger pl : passengerList)
		{
			PassengerDTO passengerDTO=new PassengerDTO();
			
			passengerDTO.setAge(pl.getAge());
			passengerDTO.setContactNumber(pl.getContactNumber());
			passengerDTO.setEmailId(pl.getEmailId());
			passengerDTO.setGender(pl.getGender());
			passengerDTO.setName(pl.getName());
			passengerDTOList.add(passengerDTO);
			
		}
		
		return null;
	
		
		
	}

}