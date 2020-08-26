package com.bookingservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.bookingservice.utility.BusCategory;

import lombok.Data;

@Entity
@Data
public class Booking {

	@Id
	@GeneratedValue
	private Long generateId;

	@Column(name = "bookingId", nullable = false, unique = true)
	private String bookingId;

	@Column(name = "customerId", nullable = false)
	private String customerId;

	private Boolean isReturnTicket;
	
	@Column(name = "contactNumber", nullable = false)
	private Long contactNumber;
	
	@Column(name = "emailId", nullable = false)
	private String emailId;
	
	@Column(name = "status", nullable = false)
	private BookingStatus status;
	
	
	@Column(name = "busNumber", nullable = false)
	private String busNumber;
	
	@Column(name = "busCategory", nullable = false)
	private BusCategory busCategory;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "booking_passenger", joinColumns = @JoinColumn(name = "bookingId"), inverseJoinColumns = @JoinColumn(name = "passengerId"))
	private List<Passenger> passengerList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "booking_Itinery", joinColumns = @JoinColumn(name = "bookingId"), inverseJoinColumns = @JoinColumn(name = "itineraryId"))
	private List<Itinerary> itinerary;
	

}
