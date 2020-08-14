package com.bookingservice.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.BookingRepo;
import com.bookingservice.entity.Booking;
import com.bookingservice.entity.BookingStatus;
import com.bookingservice.entity.Itinerary;
import com.bookingservice.exception.NoUserFoundException;
import com.bookingservice.model.BookingDTO;
import com.bookingservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepo bookingRepo;
	private final ModelMapper modelMapper;

	public void bookingCreation(BookingDTO bookingDTO) {

		Booking booking=modelMapper.map(bookingDTO,Booking.class);
		
		booking.setBookingId(RandomStringUtils.random(16, true, true) + booking.getCustomerId());
		if (booking.getIsReturnTicket()) {

			Itinerary itinerary1 = Itinerary.builder().destination(booking.getItinerary().get(0).getSource())
					.source(booking.getItinerary().get(0).getDestination())
					.timeOfDeparture(bookingDTO.getTimeOfDepartureForReturn())
					.expectedArrivalTime(bookingDTO.getExpectedArrivalTimeForReturn())
					.travelDuration(bookingDTO.getTravelDurationForReturn()).build();

			List<Itinerary> itinerarylist = booking.getItinerary();

			itinerarylist.add(itinerary1);

			booking.setItinerary(itinerarylist);

		}

		booking.setStatus(BookingStatus.BOOKED);
		
		bookingRepo.save(booking);

	}

	public List<Booking> getAllBookings() {

		return bookingRepo.findAll();
	}

	public BookingDTO getTicket(String bookingId) {

		Booking booking = bookingRepo.findByBookingId(bookingId)
				.orElseThrow(() -> new NoUserFoundException("No Data Found"));

		return modelMapper.map(booking, BookingDTO.class);

	}

	public String cancelTicket(String bookingId) {
		
		
		Booking booking = bookingRepo.findByBookingId(bookingId)
				.orElseThrow(() -> new NoUserFoundException("No Data Found"));
		
		booking.setStatus(BookingStatus.CANCELLED);
		
		bookingRepo.save(booking);
		
		return ApplicationMessage.CANCELLED_TICKET;
	}

}