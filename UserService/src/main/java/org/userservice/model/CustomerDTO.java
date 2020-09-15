package org.userservice.model;

import java.util.Set;

import org.userservice.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	private Long customerId;
	
	private String userName;

	private String custName;
	
	private String password;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

	private String token;

	private Boolean isActive;

	private Set<RoleDTO> roleList;

}
