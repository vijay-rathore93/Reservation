package com.busPortal.model;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
	
	
	private Boolean isFemaleReserved;
	private String seatNumber;
	
	private Boolean isOccupied;

}
