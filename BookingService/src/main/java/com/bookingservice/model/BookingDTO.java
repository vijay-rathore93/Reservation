package com.bookingservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class BookingDTO {
	
	private String bookingId;
	private List<PassengerDTO> passengerList; 
	
	
	private ItineraryDTO itineraryDTO;
	

}
