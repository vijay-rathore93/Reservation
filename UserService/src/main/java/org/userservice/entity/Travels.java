package org.userservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



public class Travels {

	
	private Long travelContactNumber;
	private String travelEmailId;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "travel_role", joinColumns = @JoinColumn(name = "travel_Id"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roleList;
}
