package com.bookingservice.model;

import java.sql.Timestamp;
import java.util.List;

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
