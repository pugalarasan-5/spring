package com.example.digital_payment_management.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digital_payment_management.dao.UserDao;
import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	public ResponseStructure<?> registerUser(User user){
		User data = userDao.registerUser(user);
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(data);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Register Succesfully...");
		structure.setStatusCode(200);
		return structure;
	}
	
	public ResponseStructure<?> userLogin(String email,String password){
		Optional<User> login = userDao.userLogin(email);
		if(login==null) {
			throw new IllegalArgumentException("Invalid Email id...");
		}
		User user = login.get();
		if(user.getPassword().equals(password)) {
			ResponseStructure<User> structure=new ResponseStructure<>();
			structure.setData(user);
			structure.setMessage("Login Successfully...");
			structure.setTimestamp(LocalDateTime.now());
			structure.setStatusCode(200);
			return structure;
		}
		throw new IllegalArgumentException("Password Not Match...");
	}
	
//	public ResponseStructure<?> addBank(long id) {
//		List<Bank> bank = userDao.addBank(id);
//		if(bank.isEmpty()) {
//			throw new IllegalArgumentException("Bank Not Found...");
//		}
//		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
//		structure.setData(bank);
//		structure.setMessage("Bank Added sucessfully...");
//		structure.setStatusCode(201);
//		structure.setTimestamp(LocalDateTime.now());
//		return structure;
//	}
	
	
//	GET OR FIND
	
	public ResponseStructure<?> findById(int id){
		User byId = userDao.findById(id);
		if(byId==null) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(byId);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Found !!");
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findByName(String name) {
		List<User> list = userDao.findByName(name);
		if(list.isEmpty()) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<List<User>> structure=new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("User Found...");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findByEmail(String email){
		User byEmail = userDao.findByEmail(email);
		if(byEmail==null) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(byEmail);
		structure.setMessage("User Found!!!");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findByPhone(String phone){
		User byPhone = userDao.findByPhone(phone);
		if(byPhone==null) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(byPhone);
		structure.setMessage("User Found...");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findByStatus(String status){
		List<User> list = userDao.findByStatus(status);
		if(list.isEmpty()) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<List<User>> structure=new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("User Found...");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findAll(){
		List<User> list = userDao.findAll();
		if(list.isEmpty()) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<List<User>> structure=new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("User Found...");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}

	public ResponseStructure<?> findByDate(LocalDate date) {
		List<User> list = userDao.findByDate(date);
		if(list.isEmpty()) {
			throw new IllegalArgumentException("User Not Found");
		}
		ResponseStructure<List<User>> structure=new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("User Found...");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}

	

	public ResponseStructure<?> updateUserEmail(int id, String email) {
		User user = userDao.updateUserEmail(id,email);
		if(user==null) {
			throw new IllegalArgumentException("Given Details is Already Exists...");
		}
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(user);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Updated Succesfully...");
		structure.setStatusCode(200);
		return structure;
		
	}

	public ResponseStructure<?> updateUserPassword(int id, String password) {
		User user = userDao.updateUserPassword(id,password);
		if (user == null) {
			throw new IllegalArgumentException("Given Details is Already Exists...");
		}
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(user);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Updated Succesfully...");
		structure.setStatusCode(200);
		return structure;
	}

	public ResponseStructure<?> updateUserStatus(int id, String status) {
		User user = userDao.updateUserStatus(id,status);
		if (user == null) {
			throw new IllegalArgumentException("Given Details is Already Exists...");
		}
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(user);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Updated Succesfully...");
		structure.setStatusCode(200);
		return structure;
	}

	public ResponseStructure<?> sendMoney(int senderId, int receiverId, double amount) {
		boolean sendMoney = userDao.sendMoney(senderId, receiverId, amount);
		if (sendMoney) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setData("Money Send Succesfully...");
			structure.setTimestamp(LocalDateTime.now());
			structure.setMessage("Money Send Succesfully...");
			structure.setStatusCode(200);
			return structure;
		}
		throw new IllegalArgumentException("Transaction Failed...");
	}

	public ResponseStructure<?> addBank(int userId, long accountNo) {
		List<Bank> bank = userDao.addBank(userId, accountNo);
		if (bank.isEmpty()) {
			throw new IllegalArgumentException("Bank Not Found...");
		}
		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Added sucessfully...");
		structure.setStatusCode(201);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> addMoney(int userId,long accountNo, double amount) {
		boolean addMoney = userDao.addMoney(userId,accountNo, amount);
		if (addMoney) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setData("Money Added Succesfully...");
			structure.setMessage("Money Added Succesfully...");
			structure.setStatusCode(200);
			structure.setTimestamp(LocalDateTime.now());
			return structure;
		}
		throw new IllegalArgumentException("Failed to Add Money...");
	}

	
	
}
