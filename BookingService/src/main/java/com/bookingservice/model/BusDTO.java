package com.bookingservice.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingservice.entity.Itinerary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {
	
	
	private String busNumber;

	
	private ItineraryDTO itineray;

	private List<SeatDTO> seats;
	
	

}
