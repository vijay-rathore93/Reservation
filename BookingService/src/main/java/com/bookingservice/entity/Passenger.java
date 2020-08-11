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
	private String seatNumber;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "age", nullable = false)
	private Integer age;
	
	@Column(name = "contactNumber", unique = true, nullable = false)
	private Long contactNumber;
	
	@Column(name = "emailId", unique = true, nullable = false)
	private String emailId;

}
