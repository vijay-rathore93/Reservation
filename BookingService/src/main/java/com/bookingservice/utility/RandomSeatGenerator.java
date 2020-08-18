package com.bookingservice.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bookingservice.SeatRepo;
import com.bookingservice.entity.Seat;
import com.bookingservice.model.SeatAllocatedDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RandomSeatGenerator {

	private final SeatRepo seatRepo;

	List<SeatAllocatedDTO> seatDtoList = new ArrayList<>();

	public String seatGeneration(String gender, String seatNumber) {
		seatSegregationGender();
		if (Gender.FEMALE.name().equals(gender))
			return femalePrefernceSeat(seatNumber);
		else
			return malePrefernceSeat(seatNumber);

	}

	private String femalePrefernceSeat(String seatNumber) {

		return (String) seatDtoList.stream().filter(seatAllocatedDTO -> seatAllocatedDTO.getGender() == Gender.FEMALE)
				.map(seatAllocatedDTO -> {
					String returningSeatNumber = null;

					if (seatNumber.charAt(0) == 'U' && seatAllocatedDTO.getPreference() == Preference.UPPER)
						returningSeatNumber = seatAllocatedDTO.getSeatNumber();
					else
						returningSeatNumber = seatAllocatedDTO.getSeatNumber();

					return returningSeatNumber;
				}).findFirst().get();

	}

	private String malePrefernceSeat(String seatNumber) {

		return null;

	}

	private void seatSegregationGender() {

		List<Seat> seatList = seatRepo.findAllByIsOccupied(false);

		for (Seat s : seatList) {

			if (s.getIsFemaleReserved()) {
				seatDtoList.add(new SeatAllocatedDTO(s.getSeatNumber(),
						s.getSeatNumber().charAt(0) == 'U' ? Preference.UPPER : Preference.LOWER, Gender.FEMALE));
			} else {
				seatDtoList.add(new SeatAllocatedDTO(s.getSeatNumber(),
						s.getSeatNumber().charAt(0) == 'U' ? Preference.UPPER : Preference.LOWER, Gender.MALE));
			}

		}

	}

}
