package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flightbooking.Entity.Flight;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.repository.FlightRepository;
import org.springframework.data.domain.*;

@Repository
public class FlightDao {
	@Autowired
	private FlightRepository flightrepository;
	
	//1st
	public Flight saveFlight(Flight flight) {
		return flightrepository.save(flight);
	}
	
	//2nd
	public List<Flight>getAllFlight(){
		return flightrepository.findAll();
	}
	
	//3rd
	public Optional<Flight>getFlightById(Integer id){
		return flightrepository.findById(id);
	}
	
	//4th
	public List<Flight>getFlightBySourceAndDestination(String source,String destination){
		return flightrepository.findBySourceAndDestination(source,destination);
	}
	
	//5th
	public List<Flight>getFlightByAirline(String airline){
		return flightrepository.findByAirline(airline);
	}
	
	//6th
	public Optional<Flight>updateFlight(Integer id){
		return flightrepository.findById(id);
	}
	
	//7th
	public void deleteFlight(Flight flight) {
		flightrepository.delete(flight);
	}
	
	//8th
	public Page<Flight>getFlightByPaginationAndSorting(int pageNumber,int pageSize,String field){
		return flightrepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).descending()));
		
	}
}
