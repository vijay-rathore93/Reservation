package com.busPortal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.SeatDTO;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalSeatService {

	@Value("${SEAT_SERVICE}")
	private String seatURL;

	@Value("${SEATS_SERVICE}")
	private String seatsURL;

	private final RestTemplate restTemplate;

	public SeatDTO displaySeat(String seatNumber) {

		ResponseEntity<SeatDTO> rec = restTemplate.getForEntity(seatURL + seatNumber, SeatDTO.class);

		return rec.getBody();

	}

	public List<SeatDTO> displaySeats() {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(seatsURL, ArrayList.class);

		return rec.getBody();

	}

	public String updateSeats(String busNumber) {

		restTemplate.exchange(seatURL + busNumber, HttpMethod.PATCH, null, Void.class);

		return ApplicationMessages.SEATS_AVAILABLE;

	}

}
