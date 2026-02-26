package com.flightbooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.Entity.Passenger;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	
	@Autowired
	private PassengerService passengerservice;
	
	//1st
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>>savePassenger(@RequestBody Passenger passenger){
		return passengerservice.savePassenger(passenger);
	}
	
	//2nd
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Passenger>>>getAllPassenger(){
		return passengerservice.getAllPassenger();
	}
	
	//3rd
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>>getPassengerById(@PathVariable Integer id){
		return passengerservice.getPassengerById(id);
	}
	
	//4th
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>>updatePasssenger(@RequestBody Passenger passenger){
		return passengerservice.updatePassenger(passenger);
	}
	
	//5th
	@GetMapping("/contactnumber/{contactNumber}")
	public ResponseEntity<ResponseStructure<Passenger>>getPassengerByContactNumber(@PathVariable Long contactNumber){
		return passengerservice.getPassengerByContactNumber(contactNumber);
	}
	
	//6th
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Passenger>>>getPassengerByPageAndSort(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return passengerservice.getPassengerByPageAndSort(pageNumber, pageSize,field);
	}
	
	//7th
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>deletePassenger(@PathVariable Integer id){
		return passengerservice.deletePassenger(id);
	}
}
