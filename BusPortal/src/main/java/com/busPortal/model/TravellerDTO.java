package com.busPortal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravellerDTO {

	private Long travelsId;

	private String userName;

	private String travelsName;

	private String password;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

	private String token;

	private Boolean isActive;

	private String roleName;

}