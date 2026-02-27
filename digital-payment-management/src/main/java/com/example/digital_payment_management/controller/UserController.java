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

import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.service.UserService;
import com.example.digital_payment_management.util.ResponseStructure;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		ResponseStructure<?> structure = userService.registerUser(user);
		return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
	}

	@RequestMapping("/login")
	public ResponseEntity<?> userLogin(@RequestParam String email, @RequestParam String password) {
		ResponseStructure<?> login = userService.userLogin(email, password);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

//	@RequestMapping("/addBank/{id}")
//	public ResponseEntity<?> addBank(@PathVariable long id) {
//		ResponseStructure<?> structure = userService.addBank(id);
//		return new ResponseEntity<>(structure,HttpStatus.OK);
//	}

	
//	GET OR FIND

	@RequestMapping("/get/byId/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		ResponseStructure<?> structure = userService.findById(id);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}

	@RequestMapping("/get/byName")
	public ResponseEntity<?> findByName(@RequestParam String name) {
		userService.findByName(name);
		return null;
	}

	@RequestMapping("get/byMail")
	public ResponseEntity<?> findByEmail(@RequestParam String email) {
		ResponseStructure<?> structure = userService.findByEmail(email);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}

	@RequestMapping("/get/byPhone")
	public ResponseEntity<?> findByPhone(@RequestParam String phone) {
		ResponseStructure<?> structure = userService.findByPhone(phone);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}

	@RequestMapping("/get/byStatus")
	public ResponseEntity<?> findByStatus(@RequestParam String status) {
		ResponseStructure<?> structure = userService.findByStatus(status);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}

	@RequestMapping("/get/byDate")
	public ResponseEntity<?> findByDate(@RequestParam LocalDate date) {
		ResponseStructure<?> structure = userService.findByDate(date);
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}

	@RequestMapping("/get/All")
	public ResponseEntity<?> findAll() {
		ResponseStructure<?> structure = userService.findAll();
		return new ResponseEntity<>(structure, HttpStatus.FOUND);
	}

//	UPDATE
	
	@PutMapping("/updateEmail/{id}")
	public ResponseEntity<?> updateUserEmail(@PathVariable int id, @RequestParam String email) {
		ResponseStructure<?> updateUser = userService.updateUserEmail(id,email);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<?> updateUserPassword(@PathVariable int id, @RequestParam String password) {
		ResponseStructure<?> updateUser = userService.updateUserPassword(id, password);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<?> updateUserStatus(@PathVariable int id, @RequestParam String status) {
		ResponseStructure<?> updateUser = userService.updateUserStatus(id, status);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	
	
	
	
//	SEND MONEY

	@RequestMapping("/sendMoney")
	public ResponseEntity<?> sendMoney(@RequestParam int senderId, @RequestParam int receiverId,
			@RequestParam double amount) {
		ResponseStructure<?> structure = userService.sendMoney(senderId, receiverId, amount);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	
//	ADD BANK ACCOUNT

	@RequestMapping("/addBank")
	public ResponseEntity<?> addBank(@RequestParam int userId, @RequestParam long accountNo) {
		ResponseStructure<?> structure = userService.addBank(userId, accountNo);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
//	ADD MONEY TO WALLET

	@RequestMapping("/addMoney")
	public ResponseEntity<?> addMoney(@RequestParam int userId,@RequestParam long accountNo, @RequestParam double amount) {
		ResponseStructure<?> structure = userService.addMoney(userId,accountNo, amount);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	
	
}
