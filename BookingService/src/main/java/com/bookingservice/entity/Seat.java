package com.bookingservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

@Entity
@DynamicInsert
@Data
public class Seat {
	
	
	@Id
	@GeneratedValue
	private Integer seatId;
	@ColumnDefault("false")
	private Boolean isFemaleReserved;
	private String seatNumber;
	@ColumnDefault("false")
	private Boolean isOccupied;
	
	
	
	

}
