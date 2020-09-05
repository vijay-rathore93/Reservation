package com.busPortal.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.busPortal.utility.BusCategory;
import com.busPortal.utility.BusStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {

	private String busNumber;

	private BusStatus busStatus;

	private ItineraryDTO itinerary;

	private List<SeatDTO> seats;
	private BusCategory busCategory;

}
