package com.flightbooking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.Entity.Flight;
import com.flightbooking.dao.FlightDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.exception.FlightNotFoundException;

@Service
public class FlightService {
	@Autowired
	private FlightDao flightdao;
	
	//1st
	public ResponseEntity<ResponseStructure<Flight>>saveFlight(Flight flight){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Flight record saved");
		response.setData(flightdao.saveFlight(flight));
		
		return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.CREATED);
	}
	
	//2nd
	public ResponseEntity<ResponseStructure<List<Flight>>>getAllFlights(){
		List<Flight>list=flightdao.getAllFlight();
		if(list.isEmpty()) {
			throw new FlightNotFoundException("No records available in the database");
		}
		ResponseStructure<List<Flight>>response=new ResponseStructure<List<Flight>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("All records have been fetched");
		response.setData(list);
		
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
	}
	
	//3rd
	public ResponseEntity<ResponseStructure<Flight>>getFlightById(Integer id){
		Optional<Flight>opt=flightdao.getFlightById(id);
		if(opt.isEmpty()) {
			throw new FlightNotFoundException("Record not found for the id"+id);
		}
		ResponseStructure<Flight>response=new ResponseStructure<Flight>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("flight Record found for the id"+id);
		response.setData(opt.get());
		
		return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
	}
	
	//4th
	public ResponseEntity<ResponseStructure<List<Flight>>>getFlightsBySourceAndDestination(String source,String destination){
		ResponseStructure<List<Flight>>response=new ResponseStructure<List<Flight>>();
		List<Flight>lb=flightdao.getFlightBySourceAndDestination(source,destination);
		if(!lb.isEmpty()) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("All the flight with souce "+source+" belong to the destination" +destination+"is retreive successsfully");
			response.setData(lb);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
		}
		else {
			throw new FlightNotFoundException("flight record with source" +source+" destination "+destination+" is not availlable in the db"); 
		}
	}
	
	//5th
	public ResponseEntity<ResponseStructure<List<Flight>>>getFlightsByAirline(String airline){
		ResponseStructure<List<Flight>>response=new ResponseStructure<List<Flight>>();
		List<Flight>lb=flightdao.getFlightByAirline(airline);
		if(!lb.isEmpty()) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("flight record with airline "+airline+"is retreive successsfully");
			response.setData(lb);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
		}
		else {
			throw new FlightNotFoundException("flight record with airline" +airline+" is not found in DB"); 
		}
	}
	
	//6th
	public ResponseEntity<ResponseStructure<Flight>>updateFlight(Flight flight){
		if(flight.getId()==null) {
			throw new FlightNotFoundException("must have to provide id");
		}
		Optional<Flight>opt=flightdao.getFlightById(flight.getId());
		if(opt.isEmpty()) {
			throw new FlightNotFoundException("Id doesn't exist in the DB");
		}
		ResponseStructure<Flight>response=new ResponseStructure<Flight>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("record has been updated");
		response.setData(flightdao.saveFlight(flight));
		
		return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
	}
	
	//7th
	public ResponseEntity<ResponseStructure<String>>deleteFlight(Integer id){
		
		Optional<Flight>opt=flightdao.getFlightById(id);
		if(opt.isEmpty()) {
			throw new FlightNotFoundException("No record found in the DB");
		}
		flightdao.deleteFlight(opt.get());
		ResponseStructure<String>response=new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("record has been deleted successfully");
		response.setData("deleted id"+id);
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	}
	
	//8th
	public ResponseEntity<ResponseStructure<Page<Flight>>>getFlightByPaginationAndSorting(int pageNumber,int pageSize,String field){
		Page<Flight>pb=flightdao.getFlightByPaginationAndSorting(pageNumber, pageSize, field);
		if(!pb.isEmpty()) {
			ResponseStructure<Page<Flight>>response=new ResponseStructure<>();
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("all the flight record retreive successfully");
			response.setData(flightdao.getFlightByPaginationAndSorting(pageNumber, pageSize, field));
			
			return new ResponseEntity<ResponseStructure<Page<Flight>>>(response,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("no record found in the database");
		}
	}
}
