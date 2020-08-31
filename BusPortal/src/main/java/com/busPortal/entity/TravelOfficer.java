package com.busPortal.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class TravelOfficer {
	
	
	
	private Integer travelOfficerId;
	private String toName;
	private String toPassword;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "to_role", joinColumns = {
			@JoinColumn(name = "toId", referencedColumnName = "toId") }, inverseJoinColumns = {
					@JoinColumn(name = "roleId", referencedColumnName = "roleId") })
	private Set<Role> roleList;

}
