package com.bookingservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Travels;

public interface TravelsRepo extends JpaRepository<Travels, Integer>{

}
