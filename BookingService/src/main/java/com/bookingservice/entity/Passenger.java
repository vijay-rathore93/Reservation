package com.bookingservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Passenger {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "seatNumber", nullable = false)
	private String seatNumber;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "age", nullable = false)
	private Integer age;
	
	

}
