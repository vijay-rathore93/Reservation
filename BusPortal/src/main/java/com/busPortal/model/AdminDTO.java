package com.busPortal.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminDTO {

	private Long adminId;

	private String userName;

	private String adminName;

	private String emailId;

	private String aadharNumber;

	private Long contactNumber;

	private String roleName;

	private String password;

	private String token;

	private Boolean isActive;

}
