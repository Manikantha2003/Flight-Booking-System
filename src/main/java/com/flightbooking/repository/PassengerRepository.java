package com.flightbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.Entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Integer>{
	Optional<Passenger>findByContactNumber(long contactNumber);
}
