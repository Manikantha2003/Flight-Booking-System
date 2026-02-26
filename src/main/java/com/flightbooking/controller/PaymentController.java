package com.flightbooking.controller;

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
import java.util.List;
import com.flightbooking.Entity.Payment;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.enums.Mode;
import com.flightbooking.enums.Status;
import com.flightbooking.repository.PaymentRepository;
import com.flightbooking.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;
	
	//1st
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>>createPayment(@RequestBody Payment payment){
		return paymentservice.savePayment(payment);
	}
	
	//2nd
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Payment>>>getAllPayment(){
		return paymentservice.getAllPayment();
	}
	
	//3rd
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>>getPaymentById(@PathVariable Integer id){
		return paymentservice.getPaymentById(id);
	}
	
	//4th
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByStatus(@PathVariable Status status){
		return paymentservice.getPaymentByStatus(status);
	}
	
	//5th
	@GetMapping("/amount/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByAmountGreaterThan(@PathVariable Double amount){
		return paymentservice.getPaymentByAmountGreaterThan(amount);
	}
	
	//6th
	@GetMapping("/mode/{mode}")
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByMode(@PathVariable Mode mode){
		return paymentservice.getPaymentByMode(mode);
	}
	
	//7th
	@PutMapping
	public ResponseEntity<ResponseStructure<Payment>>updatePayment(@RequestBody Payment payment){
		return paymentservice.updatePayment(payment);
	}
	
	//8th
	@GetMapping("/page/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Payment>>>getPaymentByPageAndSort(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return paymentservice.getPaymentByPageAndSort(pageNumber, pageSize, field);
	}
	//9th
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseStructure<String>>deletePayment(@PathVariable Integer id){
		return paymentservice.deletePayment(id);
	}
}
