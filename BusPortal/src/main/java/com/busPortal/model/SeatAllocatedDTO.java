package com.busPortal.model;

import com.busPortal.utility.Gender;
import com.busPortal.utility.Preference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatAllocatedDTO {

	private String seatNumber;
	private Preference preference;

	private Gender gender;
}
