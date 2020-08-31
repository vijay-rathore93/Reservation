package com.busPortal.entity;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Itinerary {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String source;
	private String destination;
	private Timestamp timeOfDeparture;
	private Timestamp expectedArrivalTime;
	private Integer travelDuration;
	

}
