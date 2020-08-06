package com.bookingservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
