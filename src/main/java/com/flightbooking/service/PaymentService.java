package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.Entity.Payment;
import com.flightbooking.dao.PaymentDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.enums.Mode;
import com.flightbooking.enums.Status;
import com.flightbooking.exception.PaymentNotFoundException;

@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentdao;
	
	//1st
	public ResponseEntity<ResponseStructure<Payment>>savePayment(Payment payment){
		ResponseStructure<Payment>response=new ResponseStructure<Payment>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Payment record saved");
		response.setData(paymentdao.savePayment(payment));
		
		return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.CREATED);
	}
	
	//2nd
	public ResponseEntity<ResponseStructure<List<Payment>>>getAllPayment(){
		List<Payment>payment=paymentdao.getAllPayment();
		if(!payment.isEmpty()) {
			ResponseStructure<List<Payment>>response=new ResponseStructure<List<Payment>>();
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("All Payment record fetched successfully");
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("Payment record not exist");
		}
	}
	
	//3rd
	public ResponseEntity<ResponseStructure<Payment>>getPaymentById(Integer id){
		Optional<Payment>opt=paymentdao.getPaymentById(id);
		if(opt.isPresent()) {
			ResponseStructure<Payment>res=new ResponseStructure<Payment>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("fetched payment record by id");
			res.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Payment>>(res,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("payment record not exist for id"+id);
		}
	}
	
	//4th
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByStatus(Status status){
		List<Payment>payment=paymentdao.getPaymentByStatus(status);
		if(!payment.isEmpty()) {
			ResponseStructure<List<Payment>>res=new ResponseStructure<List<Payment>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("payemnt record fetched by status");
			res.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(res,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("payment record not exist for status");
		}
	}
	
	//5th
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByAmountGreaterThan(Double amount){
		List<Payment>payment=paymentdao.getPaymentByAmountGreaterThan(amount);
		if(!payment.isEmpty()) {
			ResponseStructure<List<Payment>>res=new ResponseStructure<List<Payment>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("payemnt record fetched by amount greater than"+amount);
			res.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(res,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("payment record not exist for that amount");
		}
	}
	
	//6th
	public ResponseEntity<ResponseStructure<List<Payment>>>getPaymentByMode(Mode mode){
		List<Payment>payment=paymentdao.getPaymentByMode(mode);
		if(!payment.isEmpty()) {
			ResponseStructure<List<Payment>>res=new ResponseStructure<List<Payment>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("payemnt record fetched by mode");
			res.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(res,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("payment record not exist for "+mode+ " mode");
		}
	}
	
	//7th
	public ResponseEntity<ResponseStructure<Payment>>updatePayment(Payment payment){
		if(payment.getId()==null) {
			throw new PaymentNotFoundException("id must be provided");
		}else {
			Optional<Payment>opt=paymentdao.getPaymentById(payment.getId());
			if(opt.isPresent()) {
				ResponseStructure<Payment>res=new ResponseStructure<Payment>();
				res.setStatuscode(HttpStatus.OK.value());
				res.setMessage("Payment record updated");
				res.setData(paymentdao.savePayment(payment));
				return new ResponseEntity<ResponseStructure<Payment>>(res,HttpStatus.OK);
			}else {
				throw new PaymentNotFoundException("Payment record not found plz provide the valid id");
			}
		}
	}
	
	//8th
	public ResponseEntity<ResponseStructure<Page<Payment>>>getPaymentByPageAndSort(Integer pageNumber,Integer pageSize,String field){
		Page<Payment>pb=paymentdao.getPaymentByPaginationAndSorting(pageNumber, pageSize, field);
		if(!pb.isEmpty()) {
			ResponseStructure<Page<Payment>>res=new ResponseStructure<Page<Payment>>();
			res.setStatuscode(HttpStatus.OK.value());
			res.setMessage("payment record fetched by pagination and sorting");
			res.setData(pb);
			return new ResponseEntity<ResponseStructure<Page<Payment>>>(res,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("no record found in db");
		}
	}
	
	//9th
	public ResponseEntity<ResponseStructure<String>>deletePayment(Integer id){
		Optional<Payment>opt=paymentdao.getPaymentById(id);
		if(opt.isEmpty()) {
			throw new PaymentNotFoundException("No record found in the DB");
		}
		paymentdao.deletePayment(opt.get());
		ResponseStructure<String>response=new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("record has been deleted successfully");
		response.setData("deleted id"+id);
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	}
}
