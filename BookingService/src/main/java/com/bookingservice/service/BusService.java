package com.bookingservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingservice.BusRepo;
import com.bookingservice.entity.Bus;
import com.bookingservice.exception.TravelException;
import com.bookingservice.utility.BusCategory;
import com.bookingservice.utility.BusStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusService {

	private final BusRepo busRepo;

	public List<Bus> displayBuses() {

		return busRepo.findAll();
	}

	public List<Bus> activeBuses(BusStatus busStatus) {

		return busRepo.findAllByBusStatus(busStatus);
	}

	public void updateStatus(BusStatus busStatus, String busNumber) {

		Bus bus = busRepo.findByBusNumber(busNumber).orElseThrow(() -> new TravelException("Bus not found"));

		if (!bus.getBusStatus().name().equals(busStatus.name())) {
			bus.setBusStatus(busStatus);
			busRepo.save(bus);
		}

	}

	public List<Bus> busCategory(BusCategory busCategory) {

		return busRepo.findAllByBusCategory(busCategory);
	}

}
