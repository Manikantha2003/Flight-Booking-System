package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.Entity.Payment;
import com.flightbooking.enums.Mode;
import com.flightbooking.enums.Status;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	List<Payment>findByStatus(Status status);
	
	List<Payment>findByAmountGreaterThan(Double amount);
	
	List<Payment>findByMode(Mode mode);
}
