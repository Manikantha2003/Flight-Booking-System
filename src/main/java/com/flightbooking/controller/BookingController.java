package com.flightbooking.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.flightbooking.Entity.Booking;
import com.flightbooking.Entity.Passenger;
import com.flightbooking.Entity.Payment;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.enums.Status;
import com.flightbooking.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingservice;
	
	//1st
	@PostMapping("/{flight_id}")
	public ResponseEntity<ResponseStructure<Booking>>createBooking(@PathVariable Integer flight_id,@RequestBody Booking booking){
		return bookingservice.saveBooking(flight_id,booking);
	}
	
	//2nd
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Booking>>>getAllBooking(){
		return bookingservice.getAllBooking();
	}
	
	//3rd
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>>getBookingById(@PathVariable Integer id){
		return bookingservice.getBookingById(id);
	}
	//4th
	@GetMapping("/flightid/{id}")
	public ResponseEntity<ResponseStructure<List<Booking>>>getBookingByFlightId(@PathVariable Integer id){
		return bookingservice.getBookingByFlightId(id);
	}
	
	//5th
	@GetMapping("/date/{bookingDate}")
	public ResponseEntity<ResponseStructure<List<Booking>>>getBookingByDate(@PathVariable LocalDateTime bookingDate){
		return bookingservice.getBookingByDate(bookingDate);
	}
	
	//6th
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>>getBookingByStatus(@PathVariable Status status){
		return bookingservice.getBookingByStatus(status);
	}
	
	//7th
	@GetMapping("/passenger/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>>getPassengerByBookingId(@PathVariable Integer id){
		return bookingservice.getPassengerByBookingId(id);
	}
	
	//8th
	@GetMapping("/payment/{id}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByBookingId(@PathVariable Integer id){
		return bookingservice.getPaymentByBookingId(id);
	}
	
	//9th
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>>updateBooking(@PathVariable Booking id,@RequestBody Booking booking){
		return bookingservice.updateBookingStatus(id,booking.getStatus());
	}
	
	//10th
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>deleteBooking(@PathVariable Integer id){
		return bookingservice.deleteBooking(id);
	}
	
	//11th
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Booking>>>getBookingByPaginationAndSort(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return bookingservice.getBookingByPageAndSort(pageNumber,pageSize,field);
	}
	
}
