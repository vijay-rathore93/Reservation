package com.bookingservice.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.BookingRepo;
import com.bookingservice.BusRepo;
import com.bookingservice.SeatRepo;
import com.bookingservice.entity.Booking;
import com.bookingservice.entity.BookingStatus;
import com.bookingservice.entity.Bus;
import com.bookingservice.entity.Itinerary;
import com.bookingservice.entity.Seat;
import com.bookingservice.exception.BookingException;
import com.bookingservice.exception.NoUserFoundException;
import com.bookingservice.model.BookingDTO;
import com.bookingservice.model.PassengerDTO;
import com.bookingservice.utility.ApplicationMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepo bookingRepo;
	private final ModelMapper modelMapper;
	private final BusRepo busRepo;
	private final SeatRepo seatRepo;

	public void bookingCreation(BookingDTO bookingDTO) {

		Booking booking = modelMapper.map(bookingDTO, Booking.class);

		busRepo.existsByBusNumber(bookingDTO.getBusNumber())
				.orElseThrow(() -> new BookingException("No such Bus Found"));



		List<PassengerDTO> plist = bookingDTO.getPassengerList();


		int flag = 0;
		for (PassengerDTO pd : plist) {
			
			if(pd.getGender().equalsIgnoreCase("FEMALE"))
			{
				seatRepo.findBySeatNumberAndIsFemaleReservedAndIsOccupied(pd.getSeatNumber(), true, false);
			}
			else
			{
				seatRepo.findBySeatNumberAndIsFemaleReservedAndIsOccupied(pd.getSeatNumber(), false, false);
			}
			

		}

		if (flag == 0) {
			throw new BookingException("No such Seat Found");
		}

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