package com.bookingservice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Bus;

public interface BusRepo extends JpaRepository<Bus, Integer> {
	
	Optional<Boolean> existsByBusNumber(String busNumber);
	Optional<Bus> findByBusNumber(String busNumber);
	

}
