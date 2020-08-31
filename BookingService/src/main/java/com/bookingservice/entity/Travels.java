package com.bookingservice.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Travels {

	@Id
	@GeneratedValue
	private Integer generatedId;

	private Long travelContactNumber;
	private String travelEmailId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "travel_bus", joinColumns = @JoinColumn(name = "travelId"), inverseJoinColumns = @JoinColumn(name = "busId"))
	private List<Bus> buses;
	
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "travel_role", joinColumns = @JoinColumn(name = "travel_Id"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roleList;

}
