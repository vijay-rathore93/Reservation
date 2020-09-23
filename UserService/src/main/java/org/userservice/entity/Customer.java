package org.userservice.entity;

import javax.persistence.Column;
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

	@Column(name = "userName", nullable = false, unique = true)
	private String userName;

	private String custName;

	private String password;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

	private String token;

	private Boolean isActive;
	
	private String roleName;

//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_Id"), inverseJoinColumns = @JoinColumn(name = "roleId"))
//	private Set<Role> roleList;
	
	
	
	

}
