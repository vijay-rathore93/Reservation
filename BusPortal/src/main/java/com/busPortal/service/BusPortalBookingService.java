package com.busPortal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.entity.Booking;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BusPortalBookingService {

	private final RestTemplate restTemplate;
	
	
	
	public Booking bookingFetcher(String name) {
		
		ResponseEntity<Booking> rec = restTemplate.getForEntity("http://localhost:9002/booking/"+name,
				Booking.class);
	
		return rec.getBody();
		
	}
	
	
	
	
}
