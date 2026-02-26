package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.Entity.Passenger;
import com.flightbooking.dao.PassengerDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.exception.PassengerNotFoundException;

@Service
public class PassengerService {
	@Autowired
	private PassengerDao passengerdao;
	
	//1st
	public ResponseEntity<ResponseStructure<Passenger>>savePassenger(Passenger passenger){
		ResponseStructure<Passenger>response=new ResponseStructure<Passenger>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Passenger record saved");
		response.setData(passengerdao.savePassenger(passenger));
		
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.CREATED);
	}
	
	//2nd
	public ResponseEntity<ResponseStructure<List<Passenger>>>getAllPassenger(){
		List<Passenger>li=passengerdao.getAllPassenger();
		if(!li.isEmpty()) {
			ResponseStructure<List<Passenger>>response=new ResponseStructure<List<Passenger>>();
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("fetched all the passenger record successfully");
			response.setData(li);
			
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response,HttpStatus.OK);
		}else {
			throw new PassengerNotFoundException("please enter the passenger details");
		}
		
	}
	
	//3rd
	public ResponseEntity<ResponseStructure<Passenger>>getPassengerById(Integer id){
		ResponseStructure<Passenger>response=new ResponseStructure<Passenger>();
		Optional<Passenger>opt=passengerdao.getPassengerById(id);
		if(opt.isPresent()) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("fetched the passenger record by id");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
		}else {
			throw new PassengerNotFoundException("passenger data not found for id"+id);
		}
	}
	
	//4th
	public ResponseEntity<ResponseStructure<Passenger>>updatePassenger(Passenger passenger){
		ResponseStructure<Passenger>res=new ResponseStructure<Passenger>();
		if(passenger.getId()==null) {
			throw new PassengerNotFoundException("id must be provided");
		}else {
			Optional<Passenger>opt=passengerdao.getPassengerById(passenger.getId());
			if(opt.isPresent()) {
				res.setStatuscode(HttpStatus.OK.value());
				res.setMessage("passenger record updated successfully");
				res.setData(passengerdao.savePassenger(passenger));
				
				return new ResponseEntity<ResponseStructure<Passenger>>(res,HttpStatus.OK);
			}
			else {
				throw new PassengerNotFoundException("Passenger record not found for the id please provide the valid id");
			}
		}
	}
	
	//5th
	public ResponseEntity<ResponseStructure<Passenger>>getPassengerByContactNumber(Long contactNumber){
		Optional<Passenger>opt=passengerdao.getPassengerByContactNumber(contactNumber);
		if(opt.isPresent()) {
			ResponseStructure<Passenger>res=new ResponseStructure<Passenger>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("getting passenger by contact number");
			res.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Passenger>>(res,HttpStatus.OK);
		}else {
			throw new PassengerNotFoundException("passenger not found for this contact number");
		}
	}
	
	//6th
	public ResponseEntity<ResponseStructure<Page<Passenger>>>getPassengerByPageAndSort(Integer pageNumber,Integer pageSize,String field){
		Page<Passenger>pb=passengerdao.getPassengerByPageAndSort(pageNumber, pageSize, field);
		if(!pb.isEmpty()){
			ResponseStructure<Page<Passenger>>res=new ResponseStructure<>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("All the passenger record retreive successfully");
			res.setData(pb);
			return new ResponseEntity<ResponseStructure<Page<Passenger>>>(res,HttpStatus.OK);
		}else {
			throw new PassengerNotFoundException("no record found in the database");
		}
	}
	
	//7th
	public ResponseEntity<ResponseStructure<String>>deletePassenger(Integer id){
		Optional<Passenger>opt=passengerdao.getPassengerById(id);
		if(opt.isEmpty()) {
			throw new PassengerNotFoundException("No record found in the database");
		}else {
			passengerdao.deletePassenger(opt.get());
			ResponseStructure<String>res=new ResponseStructure<String>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("record has been deleted Successfully");
			res.setData("deleted id"+id);
			return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.OK);
		}
	}
}
