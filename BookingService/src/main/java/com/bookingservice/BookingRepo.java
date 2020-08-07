package com.bookingservice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

	public Optional<Booking> findByBookingId(String bookingId);

}
