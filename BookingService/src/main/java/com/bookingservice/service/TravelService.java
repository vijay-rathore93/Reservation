package com.bookingservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookingservice.TravelsRepo;
import com.bookingservice.entity.Travels;
import com.bookingservice.model.TravelsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelService {

	private final TravelsRepo travelsRepo;
	private final ModelMapper modelMapper;

	public void travelCreator(TravelsDTO travelsDTO) {

		Travels travels = modelMapper.map(travelsDTO, Travels.class);
		travelsRepo.save(travels);

	}

	public List<Travels> getAllTravels() {

		return travelsRepo.findAll();
	}

}
