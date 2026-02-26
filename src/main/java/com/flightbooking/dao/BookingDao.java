package com.flightbooking.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.flightbooking.Entity.Booking;
import com.flightbooking.Entity.Passenger;
import com.flightbooking.Entity.Payment;
import com.flightbooking.enums.Status;
import com.flightbooking.repository.BookingRepository;


@Repository
public class BookingDao {
	@Autowired
	private BookingRepository bookingrepository;
	
	//1st
	public Booking saveBooking(Booking booking) {
		return bookingrepository.save(booking);
	}
	//2nd
	public List<Booking>getAllBooking(){
		return bookingrepository.findAll();
	}
	
	//3rd
	public Optional<Booking> getBookingById(Integer id) {
		return bookingrepository.findById(id);
	}
	
	//4th
	public List<Booking>getBookingByFlightId(Integer id){
		return bookingrepository.findByFlightId(id);
	}
	//5th
	public List<Booking> getBookingByDate(LocalDateTime bookingDate){
		return bookingrepository.findByBookingDate(bookingDate);
	}
	//6th
	public List<Booking>getBookingByStatus(Status status){
		return bookingrepository.findByStatus(status);
	}
	
	//7th
	public List<Passenger>getPassengerByBookingId(Integer id){
		return bookingrepository.getPassengersByBookingId(id);
	}
	
	//8th
	public List<Payment>getPaymentByBookingId(Integer id){
		return bookingrepository.getPaymentByBookingId(id);
	}
	
	//9th
	public Optional<Booking> updateBookingStatus(Integer id){
		return bookingrepository.findById(id);
	}
	
	//10th
	public void deleteBooking(Booking booking) {
		 bookingrepository.delete(booking);
	}
	//11th
	public Page<Booking>getBookingByPageAndSort(int pageNumber,int pageSize,String field){
		return bookingrepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
}
