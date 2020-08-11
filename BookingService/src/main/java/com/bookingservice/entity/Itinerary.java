package com.bookingservice.entity;



import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Itinerary {

	@Id
	@GeneratedValue
	private Long id;
	
	private String source;
	private String destination;
	private Timestamp timeOfDeparture;
	private Timestamp expectedArrivalTime;
	private Integer travelDuration;
	private Boolean isReturnTicket;
	
	
	
	

}
