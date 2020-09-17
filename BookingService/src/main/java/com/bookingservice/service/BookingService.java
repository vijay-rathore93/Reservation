package com.bookingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.entity.Booking;
import com.bookingservice.entity.BookingStatus;
import com.bookingservice.entity.Bus;
import com.bookingservice.entity.Itinerary;
import com.bookingservice.entity.Passenger;
import com.bookingservice.entity.Seat;
import com.bookingservice.exception.BookingException;
import com.bookingservice.exception.NoUserFoundException;
import com.bookingservice.model.BookingDTO;
import com.bookingservice.repo.BookingRepo;
import com.bookingservice.repo.BusRepo;
import com.bookingservice.repo.SeatRepo;
import com.bookingservice.utility.ApplicationMessage;
import com.bookingservice.utility.SeatGeneratorXX;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepo bookingRepo;
	private final ModelMapper modelMapper;
	private final BusRepo busRepo;
	private final SeatRepo seatRepo;

	private final SeatGeneratorXX seatGeneratorXX;

	public void bookingCreation(BookingDTO bookingDTO) {

		Booking booking = modelMapper.map(bookingDTO, Booking.class);

		Bus bus = busRepo.findByBusNumber(bookingDTO.getBusNumber())
				.orElseThrow(() -> new BookingException("No such Bus Found"));

		if (!bus.getBusCategory().name().equalsIgnoreCase(bookingDTO.getBusCategory().name())) {
			throw new BookingException("Bus Number and Category don't match");
		}

		List<Passenger> plistx = booking.getPassengerList();

		plistx = seatGeneratorXX.seatGeneration(plistx, booking.getBusNumber());
		booking.setPassengerList(plistx);

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

		Booking bookingCreated = bookingRepo.save(booking);
		List<Seat> list = new ArrayList<Seat>();

		for (Passenger passenger : bookingCreated.getPassengerList()) {
			Seat seat = seatRepo.findByActualSeatNumber(bookingCreated.getBusNumber() + "_" + passenger.getSeatNumber())
					.get();
			seat.setIsOccupied(true);
			list.add(seat);
		}

		seatRepo.saveAll(list);

	}

	public List<BookingDTO> getAllBookings() {

		List<Booking> bookinglist = bookingRepo.findAll();
		List<BookingDTO> bdtoList = new ArrayList<BookingDTO>();

		for (Booking booking : bookinglist) {
			bdtoList.add(modelMapper.map(booking, BookingDTO.class));
		}

		return bdtoList;
	}

	public BookingDTO getTicket(String bookingId) {

		Booking booking = bookingRepo.findByBookingId(bookingId)
				.orElseThrow(() -> new NoUserFoundException("No Data Found"));

		return modelMapper.map(booking, BookingDTO.class);

	}

	public String cancelTicket(String bookingId) {

		Booking booking = bookingRepo.findByBookingId(bookingId)
				.orElseThrow(() -> new NoUserFoundException("No Data Found"));

		List<Passenger> passengerList = booking.getPassengerList();

		String busNumber = booking.getBusNumber();

		for (Passenger p : passengerList) {
			Seat seat = seatRepo.findByActualSeatNumber(busNumber + "_" + p.getSeatNumber()).get();

			seat.setIsOccupied(false);

			seatRepo.save(seat);
		}

		booking.setStatus(BookingStatus.CANCELLED);

		bookingRepo.save(booking);

		return ApplicationMessage.CANCELLED_TICKET;
	}

	public BookingDTO getByBookingId(String bookingId) {

		Booking booking = bookingRepo.findByBookingId(bookingId)
				.orElseThrow(() -> new BookingException("No such booking found"));

		return modelMapper.map(booking, BookingDTO.class);
	}

	public BookingDTO getByCustomerId(Long customerId) {

		Booking booking = bookingRepo.findByCustomerId(customerId)
				.orElseThrow(() -> new BookingException("No such Customer Id found"));

		return modelMapper.map(booking, BookingDTO.class);
	}

}