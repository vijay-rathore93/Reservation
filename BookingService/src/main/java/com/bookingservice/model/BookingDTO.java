package com.bookingservice.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Added Service annotaotion to both BookigDTO and ItinerearyDTO


@Service
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class BookingDTO {
	
	private String bookingId;
	private List<PassengerDTO> passengerList; 
	
	
	private ItineraryDTO itinerary;
	

}
