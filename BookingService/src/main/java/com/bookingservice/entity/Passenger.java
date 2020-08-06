package com.bookingservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Passenger {

	@Id
	@GeneratedValue
	private Long id;
	private String seatNumber;
	@NotNull
	@Column(name = "name")
	private String name;
	@NotNull
	@Column(name = "gender")
	private String gender;
	@NotNull
	@Column(name = "age")
	private Integer age;
	@NotNull
	@Column(name = "contactNumber")
	private Long contactNumber;
	@NotNull
	@Column(name = "emailId")
	private String emailId;

}
