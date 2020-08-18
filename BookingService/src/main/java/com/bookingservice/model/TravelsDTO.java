package com.bookingservice.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingservice.entity.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelsDTO {
	
	

	private Long travelContactNumber;
	private String travelEmailId;

	private List<BusDTO> buses;
	
	
	

}
