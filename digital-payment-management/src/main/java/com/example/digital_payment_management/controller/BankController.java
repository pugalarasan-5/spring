package com.example.digital_payment_management.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.service.BankService;
import com.example.digital_payment_management.util.ResponseStructure;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankService;
	
	@PostMapping("/registerBank")
	public ResponseEntity<?> registerBank(@RequestBody Bank bank){
		ResponseStructure<?> structure = bankService.registerBank(bank);
		return new ResponseEntity<>(structure,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping("/loginBank")
	public ResponseEntity<?> bankLogin(@RequestParam String email,@PathVariable int pin){
		ResponseStructure<?> bankLogin = bankService.bankLogin(email,pin);
		return new ResponseEntity<>(bankLogin,HttpStatus.OK);
	}
	
	
//	GET OR FIND
	
	@RequestMapping("/get/byAccountNo/{accountNo}")
	public ResponseEntity<?> findByAccountNo(@PathVariable long accountNo){
		ResponseStructure<?> structure = bankService.findByAccountNo(accountNo);
		return new ResponseEntity<>(structure,HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/byUserName")
	public ResponseEntity<?> findByUserName(@RequestParam String userName) {
		ResponseStructure<?> structure = bankService.findByUserName(userName);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/byBankName")
	public ResponseEntity<?> findByBankName(@RequestParam String bankName) {
		ResponseStructure<?> structure = bankService.findByBankName(bankName);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/byStatus")
	public ResponseEntity<?> findByStatus(@RequestParam String status) {
		ResponseStructure<?> structure = bankService.findByStatus(status);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/byPhone")
	public ResponseEntity<?> findByPhone(@RequestParam String phone) {
		ResponseStructure<?> structure = bankService.findByPhone(phone);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/ByEmail")
	public ResponseEntity<?> findByEmail(@RequestParam String email) {
		ResponseStructure<?> structure = bankService.findByEmail(email);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/byCreatedAt")
	public ResponseEntity<?> findByCreatedAt(@RequestParam LocalDate date) {
		ResponseStructure<?> structure = bankService.findByCreatedAt(date);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
	@RequestMapping("/get/all")
	public ResponseEntity<?> findAll() {
		ResponseStructure<?> structure = bankService.findAll();
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}
	
//	UPDATE
	
	@PutMapping("/updateBank/status/{accountNo}")
	public ResponseEntity<?> updateBankStatus(@PathVariable long accountNo, @RequestParam String status) {
		ResponseStructure<?> structure = bankService.updateBankStatus(accountNo, status);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	@PutMapping("/updateBank/pin/{accountNo}")
	public ResponseEntity<?> updateBankPin(@PathVariable long accountNo, @RequestParam int pin) {
		ResponseStructure<?> structure = bankService.updateBankPin(accountNo, pin);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	@PutMapping("/updateBank/address/{accountNo}")
	public ResponseEntity<?> updateBankAddress(@PathVariable long accountNo, @RequestParam String address) {
		ResponseStructure<?> structure = bankService.updateBankAddress(accountNo, address);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	
	
//	SEND MONEY
	
	@RequestMapping("/sendMoney")
	public ResponseEntity<?> sendMoneyByBank(@RequestParam long senderId,@RequestParam long receiverId,@RequestParam double amount){
		ResponseStructure<?> structure = bankService.sendMoneyByBank(senderId,receiverId,amount);
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
