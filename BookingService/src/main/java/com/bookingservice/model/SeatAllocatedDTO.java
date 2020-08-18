package com.bookingservice.model;

import com.bookingservice.utility.Gender;
import com.bookingservice.utility.Preference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatAllocatedDTO {

	private String seatNumber;
	private Preference preference;

	private Gender gender;
}
