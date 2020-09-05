package com.busPortal.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.busPortal.utility.BusCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

	private String bookingId;
	private String customerId;
	private List<PassengerDTO> passengerList;
	private List<ItineraryDTO> itinerary;
	private Boolean isReturnTicket;
	private Long contactNumber;
	private String emailId;
	private Timestamp timeOfDepartureForReturn;
	private Integer travelDurationForReturn;
	private Timestamp expectedArrivalTimeForReturn;
	private String status;
	private String busNumber;
	private BusCategory busCategory;

}
