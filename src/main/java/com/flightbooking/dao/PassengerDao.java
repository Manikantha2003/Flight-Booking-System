package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.Entity.Passenger;
import com.flightbooking.repository.PassengerRepository;

@Repository
public class PassengerDao {
	@Autowired
	private PassengerRepository passengerrepository;
	
	//1st
	public Passenger savePassenger(Passenger passenger) {
		return passengerrepository.save(passenger);
	}
	
	//2nd
	public List<Passenger>getAllPassenger(){
		return passengerrepository.findAll();
	}
	
	//3rd
	public Optional<Passenger>getPassengerById(Integer id){
		return passengerrepository.findById(id);
	}
	
	//4th
	public Optional<Passenger> updatePassenger(Integer id){
		return passengerrepository.findById(id);
	}
	
	//5th
	public Optional<Passenger> getPassengerByContactNumber(Long contactNumber){
		return passengerrepository.findByContactNumber(contactNumber);
	}
	
	//6th
	public Page<Passenger>getPassengerByPageAndSort(Integer pageNumber,Integer pageSize,String field){
		return passengerrepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).ascending()));
	}
	//7th
	public void deletePassenger(Passenger passenger) {
		passengerrepository.delete(passenger);
	}
}
