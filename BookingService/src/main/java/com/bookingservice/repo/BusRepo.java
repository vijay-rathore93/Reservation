package com.bookingservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Bus;
import com.bookingservice.utility.BusCategory;
import com.bookingservice.utility.BusStatus;

public interface BusRepo extends JpaRepository<Bus, Integer> {
	
	Optional<Boolean> existsByBusNumber(String busNumber);
	Optional<Bus> findByBusNumber(String busNumber);
	List<Bus> findAllByBusStatus(BusStatus busStatus);
	List<Bus> findAllByBusCategory(BusCategory busCategory);
	
	
}
