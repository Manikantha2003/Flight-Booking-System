package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.flightbooking.Entity.Payment;
import com.flightbooking.enums.Mode;
import com.flightbooking.enums.Status;
import com.flightbooking.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
	private PaymentRepository paymentrepository;
	
	//1st
	public Payment savePayment(Payment payment) {
		return paymentrepository.save(payment);
	}
	
	//2nd
	public List<Payment>getAllPayment(){
		return paymentrepository.findAll();
	}
	
	//3rd
	public Optional<Payment>getPaymentById(Integer id){
		return paymentrepository.findById(id);
	}
	
	//4th
	public List<Payment>getPaymentByStatus(Status status){
		return paymentrepository.findByStatus(status);
	}
	
	//5th
	public List<Payment>getPaymentByAmountGreaterThan(Double amount){
		return paymentrepository.findByAmountGreaterThan(amount);
	}
	
	//6th
	public List<Payment>getPaymentByMode(Mode mode){
		return paymentrepository.findByMode(mode);
	}
	
	//7th
	public Optional<Payment> updatePayment(Integer id){
		return paymentrepository.findById(id);
	}
	
	//8th
	public Page<Payment>getPaymentByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field){
		return paymentrepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).ascending()));
	}
	
	//9th
	public void deletePayment(Payment payment) {
		paymentrepository.delete(payment);
	}
}
