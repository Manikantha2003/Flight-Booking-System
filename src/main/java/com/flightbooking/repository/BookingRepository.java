package com.flightbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightbooking.Entity.Booking;
import com.flightbooking.Entity.Passenger;
import com.flightbooking.Entity.Payment;
import com.flightbooking.enums.Status;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
	
	List<Booking>findByFlightId(Integer id);
	
	List<Booking>findByBookingDate(LocalDateTime bookingDate);
	
	List<Booking>findByStatus(Status status);
	
	@Query("select b.passengers from Booking b where b.id=:id")
	List<Passenger>getPassengersByBookingId(Integer id);
	
	@Query("select b.payment from Booking b where b.id=:id")
	List<Payment>getPaymentByBookingId(Integer id);
	
	
	
}
