package com.flightbooking.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.exception.FlightNotFoundException;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleFNFE(FlightNotFoundException exception){
		ResponseStructure<String> response=new ResponseStructure<String>();
		
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("failure");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIDNFE(IdNotFoundException exception){
		ResponseStructure<String> response=new ResponseStructure<String>();
		
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Id not found since id doesn't exist in the database");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleBNFE(BookingNotFoundException exception){
		ResponseStructure<String> response=new ResponseStructure<String>();
		
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("No booking record found");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePNFE(PassengerNotFoundException exception){
		ResponseStructure<String> response=new ResponseStructure<String>();
		
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("No Passenger record found");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePNFE(PaymentNotFoundException exception){
		ResponseStructure<String> response=new ResponseStructure<String>();
		
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("No payment record found");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
}
