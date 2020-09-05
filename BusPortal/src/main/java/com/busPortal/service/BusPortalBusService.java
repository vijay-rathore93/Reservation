package com.busPortal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.BusDTO;
import com.busPortal.utility.BusCategory;
import com.busPortal.utility.BusStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusPortalBusService {

	private final RestTemplate restTemplate;

	@Value("${BUS_SERVICE}")
	private String busURL;

	@Value("${BUSES_SERVICE}")
	private String busesURL;

	@Value("${BUS_STATUS_SERVICE}")
	private String busStatusURL;

	@Value("${BUS_CATEGORY_SERVICE}")
	private String busCategoryURL;

	public List<BusDTO> displayBuses() {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(busesURL, ArrayList.class);

		return rec.getBody();

	}

	public BusDTO displayBus(String busNumber) {

		ResponseEntity<BusDTO> rec = restTemplate.getForEntity(busURL + busNumber, BusDTO.class);

		return rec.getBody();

	}

	public List<BusDTO> activeBuses(BusStatus busStatus) {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(busStatusURL + busStatus, ArrayList.class);

		return rec.getBody();

	}

	public List<BusDTO> busCategory(BusCategory busCategory) {

		ResponseEntity<ArrayList> rec = restTemplate.getForEntity(busCategoryURL + busCategory, ArrayList.class);

		return rec.getBody();

	}

	public BusDTO updateStatus(BusStatus busStatus, String busNumber) {

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.set("busStatus", busStatus.name());
		httpHeaders.set("busNumber", busNumber);

		HttpEntity httpEntity = new HttpEntity(httpHeaders);

		ResponseEntity<BusDTO> rec = restTemplate.exchange(busStatusURL, HttpMethod.PATCH, httpEntity, BusDTO.class);

		return rec.getBody();

	}

}
