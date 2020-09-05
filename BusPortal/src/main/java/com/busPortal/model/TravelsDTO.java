package com.busPortal.model;

import java.util.List;

import org.springframework.stereotype.Service;

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
