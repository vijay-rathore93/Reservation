package org.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue
	private Long customerId;

	private String name;

	private String emailId;

	private String aadharNumber;
	
	private Long contactNumber;

}
