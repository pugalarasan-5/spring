package com.example.digital_payment_management.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_payment_management.dto.TransactionDTO;
import com.example.digital_payment_management.enums.TransactionType;
import com.example.digital_payment_management.service.TransactionService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "https://spring-68si9i913-fame1.vercel.app")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/registerTrans")
	public ResponseEntity<?> RegisterTransaction(@Valid @RequestBody TransactionDTO transcationDto){
		TransactionDTO trans = transactionService.registerTransaction(transcationDto);
		return new ResponseEntity<>(trans,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/byAmount")
	public ResponseEntity<?> findByAmount(@RequestParam BigDecimal amount){
		List<TransactionDTO> byAmount = transactionService.findByAmount(amount);
		return ResponseEntity.ok(byAmount);
	}
	
	@GetMapping("/ByName")
	public ResponseEntity<?> findByBankName(@RequestParam String name){
		List<TransactionDTO> byBankName = transactionService.findByBankName(name);
		return ResponseEntity.ok(byBankName);
	}
	
	@GetMapping("/ByType")
	public ResponseEntity<?> findByType(@RequestParam TransactionType type){
		List<TransactionDTO> byType = transactionService.findByType(type);
		return ResponseEntity.ok(byType);
	}
	
	@GetMapping("/ByDate")
	public ResponseEntity<?> findByDate(@RequestParam LocalDateTime date){
		List<TransactionDTO> byDate = transactionService.findByDate(date);
		return ResponseEntity.ok(byDate);
	}
	
	@GetMapping("/ByUser")
	public ResponseEntity<?> findByUser(@RequestParam int id) {
		List<TransactionDTO> byUser = transactionService.findByUser(id);
		return ResponseEntity.ok(byUser);
	}
	
}
