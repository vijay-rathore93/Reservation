package com.busPortal.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravellerDTO {

	private Long travelsId;

	private String userName;

	private String name;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

	private String roleName;
	
	private String password;

	private String token;

	private Boolean isActive;

}