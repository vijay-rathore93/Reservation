package com.busPortal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

	private String name;
	private Integer age;
	private String gender;
	private String seatNumber;

}
