package com.bookingservice.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.BookingRepo;
import com.bookingservice.entity.Booking;
import com.bookingservice.exception.NoUserFoundException;
import com.bookingservice.model.BookingDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepo bookingRepo;
	private final ModelMapper modelMapper;

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

		Booking booking = bookingRepo.findByBookingId(bookingId)
				.orElseThrow(() -> new NoUserFoundException("No Data Found"));

		return modelMapper.map(booking, BookingDTO.class);

	}

}