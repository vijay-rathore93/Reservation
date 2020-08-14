package com.bookingservice.entity;



import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
