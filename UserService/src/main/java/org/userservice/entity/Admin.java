package org.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Admin {

	@Id
	@GeneratedValue
	private Long adminId;

	@Column(name = "userName", nullable = false, unique = true)
	private String userName;

	private String adminName;

	private String password;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

	private String token;

	private Boolean isActive;

	private String roleName;

}