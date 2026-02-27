package com.example.digital_payment_management.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_payment_management.entity.Transcation;
import com.example.digital_payment_management.service.TranscationService;

@RestController
@RequestMapping("/transcation")
public class TranscationController {
	
	@Autowired
	TranscationService transcationService;
	
	@RequestMapping("/registerTrans")
	public ResponseEntity<?> RegisterTranscation(@RequestBody Transcation transcation){
		Transcation trans = transcationService.registerTranscation(transcation);
		return new ResponseEntity<>(trans,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping("/get/byAmount")
	public ResponseEntity<?> findByAmount(double amount){
		List<Transcation> byAmount = transcationService.findByAmount(amount);
		return new ResponseEntity<>(byAmount,HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/ByName")
	public ResponseEntity<?> findByBankName(String name){
		List<Transcation> byBankName = transcationService.findByBankName(name);
		return new ResponseEntity<>(byBankName,HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/ByProcees")
	public ResponseEntity<?> findByProcess(String process){
		List<Transcation> byProcess = transcationService.findByProcess(process);
		return new ResponseEntity<>(byProcess,HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/ByDate")
	public ResponseEntity<?> findByDate(LocalDate date){
		List<Transcation> byDate = transcationService.findByDate(date);
		return new ResponseEntity<>(byDate,HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/ByUser")
	public ResponseEntity<?> findByUser(int id){
		List<Transcation> byUser = transcationService.findByUser(id);
		return new ResponseEntity<>(byUser,HttpStatus.FOUND);
	}
	
}
