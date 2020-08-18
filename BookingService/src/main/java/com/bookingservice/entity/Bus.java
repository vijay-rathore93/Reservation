package com.bookingservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Bus {

	@Id
	@GeneratedValue
	private Integer generateId;

	private String busNumber;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "bus_Itinery")
	private Itinerary itineray;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "bus_seat", joinColumns = @JoinColumn(name = "busId"), inverseJoinColumns = @JoinColumn(name = "seatId"))
	private List<Seat> seats;

}
