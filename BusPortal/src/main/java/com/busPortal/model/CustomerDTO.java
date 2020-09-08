package com.busPortal.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	
	private Long customerId;

	private String custName;
	
	private String password;

	private String emailId;

	private String aadharNumber;
	
	private Long contactNumber;
	
	private String custPassword;
	
	private String token;
	
	private Boolean isActive;
	
	private Set<RoleDTO> roleList;

}
