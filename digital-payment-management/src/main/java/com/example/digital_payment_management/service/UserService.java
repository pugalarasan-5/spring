package com.example.digital_payment_management.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digital_payment_management.dao.UserDao;
import com.example.digital_payment_management.dto.BankDTO;
import com.example.digital_payment_management.dto.LoginRequestDTO;
import com.example.digital_payment_management.dto.UserDTO;
import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.enums.UserStatus;
import com.example.digital_payment_management.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	@Autowired
	ModelMapper mapper;
	
	public ResponseStructure<?> registerUser(UserDTO userDto){
		User user = mapper.map(userDto, User.class);
		User data = userDao.registerUser(user);
		UserDTO map = mapper.map(data, UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Register Succesfully...");
		structure.setStatusCode(200);
		return structure;
	}
	
	public ResponseStructure<?> userLogin(LoginRequestDTO loginDTO){
		String email = loginDTO.getEmail();
		String password = loginDTO.getPassword();
		Optional<User> login = userDao.userLogin(email);
		if(login==null) {
			throw new IllegalArgumentException("Invalid Email id...");
		}
		User user = login.get();
		if(user.getPassword().equals(password)) {
			UserDTO map = mapper.map(user, UserDTO.class);
			ResponseStructure<UserDTO> structure=new ResponseStructure<>();
			structure.setData(map);
			structure.setMessage("Login Successfully...");
			structure.setTimestamp(LocalDateTime.now());
			structure.setStatusCode(200);
			return structure;
		}
		throw new IllegalArgumentException("Password Not Match...");
	}
	
	
	
//	GET OR FIND
	
	public ResponseStructure<?> findById(int id){
		User user = userDao.findById(id);
		if(user==null) {
			throw new IllegalArgumentException("User Not Found");
		}
		UserDTO map = mapper.map(user,UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Found !!");
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findByUserName(String userName) {
		List<User> list = userDao.findByUserName(userName);
		if(list.isEmpty()) {
			throw new IllegalArgumentException("User Not Found");
		}
		List<UserDTO> map = list.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
		ResponseStructure<List<UserDTO>> structure=new ResponseStructure<>();
		structure.setData(map);
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
		UserDTO map = mapper.map(byEmail, UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
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
		UserDTO map = mapper.map(byPhone, UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setMessage("User Found...");
		structure.setTimestamp(LocalDateTime.now());
		structure.setStatusCode(201);
		return structure;
	}
	
	public ResponseStructure<?> findByStatus(UserStatus status){
		List<User> list = userDao.findByStatus(status);
		if(list.isEmpty()) {
			throw new IllegalArgumentException("User Not Found");
		}
		List<UserDTO> map = list.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
		ResponseStructure<List<UserDTO>> structure=new ResponseStructure<>();
		structure.setData(map);
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
		List<UserDTO> map = list.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
		ResponseStructure<List<UserDTO>> structure=new ResponseStructure<>();
		structure.setData(map);
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
		List<UserDTO> map = list.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
		ResponseStructure<List<UserDTO>> structure=new ResponseStructure<>();
		structure.setData(map);
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
		UserDTO map = mapper.map(user, UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
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
		UserDTO map = mapper.map(user, UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Updated Succesfully...");
		structure.setStatusCode(200);
		return structure;
	}

	public ResponseStructure<?> updateUserStatus(int id, UserStatus status) {
		User user = userDao.updateUserStatus(id,status);
		if (user == null) {
			throw new IllegalArgumentException("Given Details is Already Exists...");
		}
		UserDTO map = mapper.map(user, UserDTO.class);
		ResponseStructure<UserDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setTimestamp(LocalDateTime.now());
		structure.setMessage("User Updated Succesfully...");
		structure.setStatusCode(200);
		return structure;
	}

	public ResponseStructure<?> sendMoney(int senderId, int receiverId, BigDecimal amount) {
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
		List<BankDTO> list = bank.stream().map(b -> mapper.map(b, BankDTO.class)).toList();
		ResponseStructure<List<BankDTO>> structure = new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("Bank Added sucessfully...");
		structure.setStatusCode(201);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> addMoney(int userId,long accountNo, BigDecimal amount) {
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
