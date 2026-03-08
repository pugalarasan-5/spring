package com.example.digital_payment_management.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_payment_management.dto.BankDTO;
import com.example.digital_payment_management.dto.BankLoginDTO;
import com.example.digital_payment_management.enums.BankStatus;
import com.example.digital_payment_management.service.BankService;
import com.example.digital_payment_management.util.ResponseStructure;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankService;

	@PostMapping
	public ResponseEntity<?> registerBank(@RequestBody BankDTO bankDto) {
		ResponseStructure<?> structure = bankService.registerBank(bankDto);
		return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);

	}
//
	@PostMapping("/login")
	public ResponseEntity<?> bankLogin(@RequestBody BankLoginDTO loginDTO) {
		return ResponseEntity.ok(bankService.bankLogin(loginDTO));
	}
//
//	GET OR FIND

	@GetMapping("/{accountNo}")
	public ResponseEntity<?> findByAccountNo(@PathVariable long accountNo) {
		return ResponseEntity.ok(bankService.findByAccountNo(accountNo));
	}
//
	@GetMapping("/byUserName")
	public ResponseEntity<?> findByUserName(@RequestParam String userName) {
		return ResponseEntity.ok(bankService.findByUserName(userName));
	}
//
	@GetMapping("/byBankName")
	public ResponseEntity<?> findByBankName(@RequestParam String bankName) {
		return ResponseEntity.ok(bankService.findByBankName(bankName));
	}
//
	@GetMapping("/byStatus")
	public ResponseEntity<?> findByStatus(@RequestParam BankStatus status) {
		return ResponseEntity.ok(bankService.findByStatus(status));
	}
//
	@GetMapping("/byPhone")
	public ResponseEntity<?> findByPhone(@RequestParam String phone) {
		return ResponseEntity.ok(bankService.findByPhone(phone));
	}
//
	@GetMapping("/byEmail")
	public ResponseEntity<?> findByEmail(@RequestParam String email) {
		return ResponseEntity.ok(bankService.findByEmail(email));
	}
//
	@GetMapping("/byCreatedAt")
	public ResponseEntity<?> findByCreatedAt(@RequestParam LocalDate date) {
		return ResponseEntity.ok(bankService.findByCreatedAt(date));
	}
//
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(bankService.findAll());
	}
//	

//	UPDATE

	@PutMapping("/updateStatus")
	public ResponseEntity<?> updateBankStatus(@RequestParam long accountNo,
	                                          @RequestParam BankStatus status) {
		return ResponseEntity.ok(bankService.updateBankStatus(accountNo, status));
	}
//
	@PutMapping("/UpdatePin/{accountNo}")
	public ResponseEntity<?> updateBankPin(@PathVariable long accountNo, @RequestParam String pin) {
		return ResponseEntity.ok(bankService.updateBankPin(accountNo, pin));
	}
//
	@PutMapping("/updateAddress/{accountNo}")
	public ResponseEntity<?> updateBankAddress(@PathVariable long accountNo, @RequestParam String address) {
		return ResponseEntity.ok(bankService.updateBankAddress(accountNo, address));
	}
//
//	SEND MONEY

	@PostMapping("/sendMoney")
	public ResponseEntity<?> sendMoneyByBank(@RequestParam long senderAccountNo, @RequestParam long receiverAccountNo,
			@RequestParam BigDecimal amount) {
		return ResponseEntity.ok(bankService.sendMoneyByBank(senderAccountNo, receiverAccountNo, amount));

	}
	@GetMapping("/getUserId")
	public ResponseEntity<?> getUserIdByAccountNo(@RequestParam int id) {
		return ResponseEntity.ok(bankService.getByUserId(id));
	}

}
