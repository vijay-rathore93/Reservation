package com.bookingservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Travels;

public interface TravelsRepo extends JpaRepository<Travels, Integer>{
	
	
	Optional<Travels> findByTravelContactNumber(String travelContactNumber);

}
