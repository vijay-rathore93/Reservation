package com.busPortal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.BookingDTO;
import com.busPortal.utility.ApplicationMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalBookingService {

	private final RestTemplate restTemplate;

	@Value("${BOOKING_SERVICE}")
	private String bookingURL;
	
	@Value("${BOOKING_SERVICE_PLURAL}")
	private String bookingsURL;
	
	@Value("${BOOKING_SERVICE_1}")
	private String bookingURL1;

	@Value("${BOOKING_SERVICE_TICKET}")
	private String ticketURL;

	private final BusPortalCustomerService busPortalCustomerService;

	public BookingDTO bookingCreation(BookingDTO bookingDTO) {

		bookingDTO.setCustomerId(busPortalCustomerService
				.getCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()).getCustomerId());

		HttpHeaders httpHeader = new HttpHeaders();
		HttpEntity<BookingDTO> hhtEntity = new HttpEntity<BookingDTO>(bookingDTO, httpHeader);

		ResponseEntity<BookingDTO> rec = restTemplate.postForEntity(bookingURL1, hhtEntity, BookingDTO.class);

		return rec.getBody();

	}

	public BookingDTO bookingGetter(String name) {

		ResponseEntity<BookingDTO> rec = restTemplate.getForEntity(bookingURL + name, BookingDTO.class);

		return rec.getBody();

	}

	public List<BookingDTO> getAllBookings() {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(bookingsURL, ArrayList.class);

		return rec.getBody();

	}

	public BookingDTO getTicket(String bookingId) {

		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.set("bookingId", bookingId);
		HttpEntity hhtEntity = new HttpEntity(httpHeader);

		ResponseEntity<BookingDTO> rec = restTemplate.exchange(ticketURL, HttpMethod.GET, hhtEntity, BookingDTO.class);

		return rec.getBody();

	}

	public String cancelTicket(String bookingId) {

		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.set("bookingId", bookingId);
		HttpEntity hhtEntity = new HttpEntity(httpHeader);

		ResponseEntity<BookingDTO> rec = restTemplate.exchange(ticketURL, HttpMethod.GET, hhtEntity, BookingDTO.class);

		return ApplicationMessages.CANCELLED_TICKET;

	}

}
