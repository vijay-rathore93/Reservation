package org.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginCredentials {
	
	@Id
	@GeneratedValue
	private Long loginId;
	
	@Column(name = "userName", nullable = false, unique = true)
	private String userName;
	
	private String password;
	
	private String roleName;
	
	private Boolean isActive;
	
	private String token;


	

}
