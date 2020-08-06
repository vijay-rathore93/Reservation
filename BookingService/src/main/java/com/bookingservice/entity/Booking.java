package com.bookingservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Booking {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "bookingId",nullable = false, unique = true)
	private String bookingId;
	
	@Column(name = "customerId",nullable = false)
	private String customerId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "booking_passenger", joinColumns = @JoinColumn(name = "bookingId"), inverseJoinColumns = @JoinColumn(name = "passengerId"))
	private List<Passenger> passengerList;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private Itinerary itinerary;

}
