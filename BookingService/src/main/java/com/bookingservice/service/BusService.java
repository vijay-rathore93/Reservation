package com.bookingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.entity.Bus;
import com.bookingservice.exception.TravelException;
import com.bookingservice.model.BusDTO;
import com.bookingservice.repo.BusRepo;
import com.bookingservice.utility.BusCategory;
import com.bookingservice.utility.BusStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusService {

	private final BusRepo busRepo;
	private final ModelMapper modelMapper;

	public List<BusDTO> displayBuses() {

		List<Bus> busList = busRepo.findAll();

		List<BusDTO> busDTOList = new ArrayList<BusDTO>();

		for (Bus bus : busList) {
			busDTOList.add(modelMapper.map(bus, BusDTO.class));
		}

		return busDTOList;
	}

	public List<BusDTO> activeBuses(String busStatus) {
		
		
		
		Bus busx=busRepo.findByBusStatus(BusStatus.valueOf(busStatus)).orElseThrow(() -> new TravelException("Bus not found"));

		List<Bus> busList = busRepo.findAllByBusStatus(BusStatus.valueOf(busStatus));
		
//		if(busList.isEmpty())
//		{
//			throw new TravelException("Bus not found");
//		}

		List<BusDTO> busDTOList = new ArrayList<BusDTO>();

		for (Bus bus : busList) {
			busDTOList.add(modelMapper.map(bus, BusDTO.class));
		}

		return busDTOList;
	}

	public void updateStatus(BusStatus busStatus, String busNumber) {

		Bus bus = busRepo.findByBusNumber(busNumber).orElseThrow(() -> new TravelException("Bus not found"));

		if (!bus.getBusStatus().name().equals(busStatus.name())) {
			bus.setBusStatus(busStatus);
			busRepo.save(bus);
		}

	}

	public List<BusDTO> busCategory(BusCategory busCategory) {

		List<Bus> buslist = busRepo.findAllByBusCategory(busCategory);

		List<BusDTO> busDTOList = new ArrayList<BusDTO>();

		for (Bus bus : buslist) {
			busDTOList.add(modelMapper.map(bus, BusDTO.class));
		}

		return busDTOList;
	}

	public BusDTO displayBus(String busNumber) {
		
		Bus bus=busRepo.findByBusNumber(busNumber).orElseThrow(() -> new TravelException("Bus not found"));
		
		return modelMapper.map(bus, BusDTO.class);
	}



}
