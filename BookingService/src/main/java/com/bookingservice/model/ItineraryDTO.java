package com.bookingservice.model;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDTO {

	private String source;
	private String destination;
	private Integer totalJourneyHours;
	private Timestamp departureTime;
	private Timestamp arrivalTime;
	private Integer ticketAmount;

}
