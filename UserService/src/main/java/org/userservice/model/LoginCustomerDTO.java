package org.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCustomerDTO {

	private Long loginId;

	private String userName;

	private String password;

	private String roleName;

	private Boolean isActive;

	private String token;

	private Long customerId;

	private String name;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

}
