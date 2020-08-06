package com.bookingservice.service;

import org.springframework.stereotype.Service;

import com.bookingservice.BookingRepo;
import com.bookingservice.entity.Booking;
import com.bookingservice.utility.AlphaNumeric;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepo bookingRepo;

	public void bookingCreation(Booking booking) {

		
		AlphaNumeric alphaNumeric=new AlphaNumeric();
		
		alphaNumeric.getBookingIdValue();
		
		bookingRepo.save(booking);

		// BookingId should be generated ALphanumeric 16 digit

		// gender will be one of male,female,other (using enum)
		
		

	}

}
