package org.userservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue
	private Long customerId;

	private String custName;
	
	private String password;

	private String emailId;

	private String aadharNumber;
	
	private Long contactNumber;
	
	private String token;
	
	private Boolean isActive;
	

	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_Id"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roleList;

}
