package org.userservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {

	private Long loginId;

	private String userName;

	private String password;

	private String roleName;

	private Boolean isActive;

	private String token;
	
	

}
