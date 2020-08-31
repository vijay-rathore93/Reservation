package com.bookingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.TravelsRepo;
import com.bookingservice.entity.Bus;
import com.bookingservice.entity.Seat;
import com.bookingservice.entity.Travels;
import com.bookingservice.exception.TravelException;
import com.bookingservice.model.TravelsDTO;
import com.bookingservice.utility.BusStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelService {

	private final TravelsRepo travelsRepo;
	private final ModelMapper modelMapper;

	public void travelCreator(TravelsDTO travelsDTO) {

		Travels travels = modelMapper.map(travelsDTO, Travels.class);

		List<Bus> busList = travels.getBuses();

		for (Bus b : busList) {
			if (b.getBusCategory() == null) {
				throw new TravelException("No such category");
			}
			b.setBusStatus(BusStatus.ACTIVE);
			List<Seat> seatList = b.getSeats();

			for (Seat s : seatList) {
				s.setActualSeatNumber(b.getBusNumber() + "_" + s.getSeatNumber());

			}
		}

		travelsRepo.save(travels);

	}

	public List<TravelsDTO> getAllTravels() {

		List<Travels> travelsList = travelsRepo.findAll();

		List<TravelsDTO> travelsDTOList = new ArrayList<TravelsDTO>();

		for (Travels travels : travelsList) {
			travelsDTOList.add(modelMapper.map(travels, TravelsDTO.class));
		}

		return travelsDTOList;
	}

}
