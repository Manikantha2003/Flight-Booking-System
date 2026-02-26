package com.flightbooking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.Entity.Booking;
import com.flightbooking.Entity.Flight;
import com.flightbooking.Entity.Passenger;
import com.flightbooking.Entity.Payment;
import com.flightbooking.dao.BookingDao;
import com.flightbooking.dao.FlightDao;
import com.flightbooking.dao.PassengerDao;
import com.flightbooking.dao.PaymentDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.enums.Status;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.exception.PassengerNotFoundException;
import com.flightbooking.exception.PaymentNotFoundException;

@Service
public class BookingService {
	@Autowired
	private BookingDao bookingdao;
	@Autowired
	private FlightDao flightdao;
	@Autowired
	private PaymentDao paymentdao;
	@Autowired
	private PassengerDao passengerdao;
	//1st
	public ResponseEntity<ResponseStructure<Booking>>saveBooking(Integer flight_id,Booking booking){
		Optional<Flight>opt=flightdao.getFlightById(flight_id);
		if(!opt.isEmpty()) {
			Flight flight=opt.get();
			booking.setFlight(flight);
		}else {
			throw new FlightNotFoundException("flight id not found"+flight_id);
		}
		//check record is present for passenger or not
		if(booking.getPassengers()!=null) {
			for(Passenger p:booking.getPassengers()) {
				p.setBooking(booking);
			}
		}
		else {
			throw new PassengerNotFoundException("please enter the passenger details");
		}
		Integer passcount=booking.getPassengers().size();
		Double totalamount=opt.get().getPrice()*passcount;
		//check for payment record is present or not
		if(booking.getPayment()!=null) {
			booking.getPayment().setAmount(totalamount);
			booking.getPayment().setBooking(booking);
		}else {
			throw new PaymentNotFoundException("enter the payment details");
		}
		ResponseStructure<Booking>res=new ResponseStructure<Booking>();
		res.setStatuscode(HttpStatus.CREATED.value());
		res.setMessage("Booking record saved Successfully");
		res.setData(bookingdao.saveBooking(booking));
		return new ResponseEntity<ResponseStructure<Booking>>(res,HttpStatus.CREATED);	
		
	}
	
	//2nd
	public ResponseEntity<ResponseStructure<List<Booking>>>getAllBooking(){
		ResponseStructure<List<Booking>>response=new ResponseStructure<List<Booking>>();
		List<Booking>bookings=bookingdao.getAllBooking();
		if(!bookings.isEmpty()) {
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("All booking records fetched successfully");
			response.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(response,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("no record found in db");
		}
	}
	
	//3rd
	public ResponseEntity<ResponseStructure<Booking>>getBookingById(Integer id){
		ResponseStructure<Booking> res=new ResponseStructure<Booking>();
		Optional<Booking>opt=bookingdao.getBookingById(id);
		if(opt.isPresent()) {
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("record fetched by id");
			res.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Booking>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("id not exist in the database");
		}
	}
	
	//4th
	public ResponseEntity<ResponseStructure<List<Booking>>>getBookingByFlightId(Integer id){
		List<Booking>bookings=bookingdao.getBookingByFlightId(id);
		if(!bookings.isEmpty()) {
			ResponseStructure<List<Booking>> res=new ResponseStructure<List<Booking>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("fetched booking record by flightid");
			res.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(res,HttpStatus.OK);
		}else {
			throw new FlightNotFoundException("Flight id not found");
		}
	}
	
	//5th
	public ResponseEntity<ResponseStructure<List<Booking>>>getBookingByDate(LocalDateTime bookingDate){
		List<Booking>bookings=bookingdao.getBookingByDate(bookingDate);
		if(!bookings.isEmpty()) {
			ResponseStructure<List<Booking>> res=new ResponseStructure<List<Booking>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("fetched booking record by date");
			res.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("booking record not exist");
		}
	}
	
	//6th
	public ResponseEntity<ResponseStructure<List<Booking>>>getBookingByStatus(Status status){
		List<Booking>bookings=bookingdao.getBookingByStatus(status);
		if(!bookings.isEmpty()) {
			ResponseStructure<List<Booking>> res=new ResponseStructure<List<Booking>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("fetched booking record by status");
			res.setData(bookings);
			return new ResponseEntity<ResponseStructure<List<Booking>>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("booking record not exist");
		}
	}
	
	//7th
	public ResponseEntity<ResponseStructure<List<Passenger>>>getPassengerByBookingId(Integer id){
		List<Passenger>passenger=bookingdao.getPassengerByBookingId(id);
		if(!passenger.isEmpty()) {
			ResponseStructure<List<Passenger>> res=new ResponseStructure<List<Passenger>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("fetched passenger record by Booking id");
			res.setData(passenger);
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("passengers by booing id not found");
		}
	}
	
	//8th
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByBookingId(Integer id){
		List<Payment>payment=bookingdao.getPaymentByBookingId(id);
		if(!payment.isEmpty()) {
			ResponseStructure<List<Payment>> res=new ResponseStructure<List<Payment>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("fetched payment record by Booking id");
			res.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("payment record by booking id not found");
		}
	}
	
	//9th
	public ResponseEntity<ResponseStructure<Booking>>updateBookingStatus(Booking id,Status status){
		Booking booking=bookingdao.saveBooking(id);
		if(booking!=null) {
			booking.setStatus(status);
			bookingdao.saveBooking(booking);
			ResponseStructure<Booking>res=new ResponseStructure<Booking>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("Status updated successfully");
			res.setData(booking);
			return new ResponseEntity<ResponseStructure<Booking>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("booking not found");
		}
	}

	//10th
	public ResponseEntity<ResponseStructure<String>>deleteBooking(Integer id){
			Optional<Booking>opt=bookingdao.getBookingById(id);
			if(opt.isPresent()) {
				ResponseStructure<String>res=new ResponseStructure<String>();
				res.setStatuscode(HttpStatus.OK.value());
				res.setMessage("delete record successfully");
				res.setData("deleted id:"+id);
				
				return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.OK);
			}else {
				throw new BookingNotFoundException("id is not available in the database");
			}
	}
	
	//11th
	public ResponseEntity<ResponseStructure<Page<Booking>>>getBookingByPageAndSort(int pageNumber,int pageSize,String field){
		Page<Booking>pb=bookingdao.getBookingByPageAndSort(pageNumber, pageSize, field);
		if(!pb.isEmpty()) {
			ResponseStructure<Page<Booking>>res=new ResponseStructure<Page<Booking>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("Get book by pagination and sorting");
			res.setData(pb);
			
			return new ResponseEntity<ResponseStructure<Page<Booking>>>(res,HttpStatus.OK);
		}else {
			throw new BookingNotFoundException("No record found in the database");
		}
		
	}
}
