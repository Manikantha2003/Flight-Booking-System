package com.flightbooking.controller;
import java.util.List;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flightbooking.Entity.Flight;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.service.FlightService;
@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightservice;
	
	//1st
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>>addFlight(@RequestBody Flight flight){
		return flightservice.saveFlight(flight);
	}
	
	//2nd
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Flight>>>getAllFlights(){
		return flightservice.getAllFlights();
	}
	
	//3rd
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Flight>>getFlightById(@PathVariable Integer id){
		return flightservice.getFlightById(id);
	}
	
	//4th
	@GetMapping("/source/{source}/destination/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>>getFlightsBySourceAndDestination(@PathVariable String source,@PathVariable String destination){
		return flightservice.getFlightsBySourceAndDestination(source, destination);
	}
	
	//5th
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>>getFlightByAirline(@PathVariable String airline){
		return flightservice.getFlightsByAirline(airline);
	}
	
	//6th
	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>>updateFlight(@RequestBody Flight flight){
		return flightservice.updateFlight(flight);
	}
	
	//7th
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>deleteFlight(@PathVariable Integer id){
		return flightservice.deleteFlight(id);
	}
	
	//8th
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Flight>>>getFlightByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
		return flightservice.getFlightByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
