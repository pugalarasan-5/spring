package com.example.digital_payment_management.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.digital_payment_management.dto.LoginRequestDTO;
import com.example.digital_payment_management.dto.UserDTO;
import com.example.digital_payment_management.enums.UserStatus;
import com.example.digital_payment_management.service.UserService;
import com.example.digital_payment_management.util.ResponseStructure;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {
		ResponseStructure<?> structure = userService.registerUser(userDto);
		return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody LoginRequestDTO loginDTO) {
		System.out.println("Login Request: " + loginDTO);
		return ResponseEntity.ok(userService.userLogin(loginDTO));
	}

	
//	GET OR FIND

	@GetMapping("/byId/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@GetMapping("/byUserName")
	public ResponseEntity<?> findByName(@RequestParam String userName) {
		return ResponseEntity.ok(userService.findByUserName(userName));
	}

	@GetMapping("/byMail")
	public ResponseEntity<?> findByEmail(@RequestParam String email) {
		return ResponseEntity.ok(userService.findByEmail(email));
	}

	@GetMapping("/byPhone")
	public ResponseEntity<?> findByPhone(@RequestParam String phone) {
		return ResponseEntity.ok(userService.findByPhone(phone));
	}

	@GetMapping("/byStatus")
	public ResponseEntity<?> findByStatus(@RequestParam UserStatus status) {
		return ResponseEntity.ok(userService.findByStatus(status));
	}
	

	@GetMapping("/byDate")
	public ResponseEntity<?> findByDate(@RequestParam LocalDate date) {
		return ResponseEntity.ok(userService.findByDate(date));
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

//	UPDATE
	
	@PutMapping("/updateEmail/{id}")
	public ResponseEntity<?> updateUserEmail(@PathVariable int id, @RequestParam String email) {
		return ResponseEntity.ok( userService.updateUserEmail(id,email));
	}
//	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<?> updateUserPassword(@PathVariable int id, @RequestParam String password) {
		return ResponseEntity.ok(userService.updateUserPassword(id, password));
	}
//	
	@PutMapping("/updateStatus/{id}")
	
	public ResponseEntity<?> updateUserStatus(@PathVariable int id, @RequestParam UserStatus status) {
		return ResponseEntity.ok(userService.updateUserStatus(id, status));
	}
//	
	
	
	
	
//	SEND MONEY

	@PutMapping("/sendMoney")
	public ResponseEntity<?> sendMoney(@RequestParam int senderId, @RequestParam int receiverId,
			@RequestParam BigDecimal amount) {
		return ResponseEntity.ok(userService.sendMoney(senderId, receiverId, amount));
	}
//	
	
//	ADD BANK ACCOUNT

	@PostMapping("/addBank")
	public ResponseEntity<?> addBank(@RequestParam int userId, @RequestParam long accountNo) {
		return ResponseEntity.ok(userService.addBank(userId, accountNo));
	}
//	
//	ADD MONEY TO WALLET

	@PostMapping("/addMoney")
	public ResponseEntity<?> addMoney(@RequestParam int userId,@RequestParam long accountNo, @RequestParam BigDecimal amount) {
		return ResponseEntity.ok(userService.addMoney(userId,accountNo, amount));
	}
	@RequestMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}
	
	
	
}
