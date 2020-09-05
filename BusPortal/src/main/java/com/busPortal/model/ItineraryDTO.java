package com.busPortal.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDTO {

	private String source;
	private String destination;
	private Timestamp timeOfDeparture;
	private Timestamp expectedArrivalTime;
	private Integer travelDuration;
	

}
