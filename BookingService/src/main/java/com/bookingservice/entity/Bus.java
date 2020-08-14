package com.bookingservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Bus {
	
	
	@Id
	@GeneratedValue
	private Integer generateId;
	
	@ElementCollection
	private List<String> seatNumbers;
	

	private String busNumber;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "bus_Itinery")
	private Itinerary itineray;
}
