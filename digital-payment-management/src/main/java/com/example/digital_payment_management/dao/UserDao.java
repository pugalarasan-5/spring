package com.example.digital_payment_management.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.entity.Transcation;
import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.repository.BankRepository;
import com.example.digital_payment_management.repository.TranscationRepository;
import com.example.digital_payment_management.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	TranscationRepository transcationRepository;
	
	@Autowired
	User user;
	
	public User registerUser(User user) {
		Optional<User> email = userRepository.findByEmail(user.getEmail());
		Optional<User> phone = userRepository.findByPhone(user.getPhone());
		user.setCreatedAt(java.time.LocalDate.now());
		if(email==null && phone==null) {
			return userRepository.save(user);
		}
		else {
			throw new IllegalArgumentException("Email/Phone details is already exists");
		}
	}
	
	public Optional<User> userLogin(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	public User findById(int id) {
		return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id.."));
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("Invalid Email..."));
	}
	
	public User findByPhone(String phone) {
		return userRepository.findByPhone(phone).orElseThrow(()->new IllegalArgumentException("Invalid Phone Number.."));
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findByStatus(String status) {
		return userRepository.findByStatus(status);
	}
	
	public List<User> findByDate(LocalDate date){
		return userRepository.findByCreatedAt(date);
	}

//	public List<Bank>  addBank(long id) {
//		Bank bank = bankRepository.findByAccountNo(id).orElseThrow(()->new IllegalArgumentException("Invalid Account No..."));
//		return user.setAccounts(bank);
//	}

	public User updateUserEmail(int id, String email) {
		User update = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		Optional<User> newEmail = userRepository.findByEmail(email);
		if(update.getEmail().equals(email)) {
			update.setEmail(email);
			userRepository.save(update);
		}
		if(newEmail.isEmpty()) {
			update.setEmail(email);
			userRepository.save(update);
		}
		else {
			throw new IllegalArgumentException("Email Already Exists...");
		}
		return update;
	}

	public User updateUserPassword(int id, String password) {
		User update = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		update.setPassword(password);
		return userRepository.save(update);
	}

	public User updateUserStatus(int id, String status) {
		User update = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		if (update.getStatus().equalsIgnoreCase(status)) {
			throw new IllegalArgumentException("User is already " + status);
		}
		update.setStatus(status);
		return userRepository.save(update);
	}

	
	
	
	
	public boolean sendMoney(int senderId, int receiverId, double amount) {
		User sender = userRepository.findById(senderId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Sender Id..."));
		User receiver = userRepository.findById(receiverId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Receiver Id..."));
		if (sender.getWalletBalance() >= amount) {
			sender.setWalletBalance(sender.getWalletBalance() - amount);
			receiver.setWalletBalance(receiver.getWalletBalance() + amount);
			userRepository.save(sender);
			userRepository.save(receiver);
			transcationRepository.save(new Transcation(amount, "Wallet", "Debit", java.time.LocalDate.now(), java.time.LocalTime.now(), sender));
			transcationRepository.save(new Transcation(amount, "Wallet", "Credit", java.time.LocalDate.now(), java.time.LocalTime.now(), sender));
			return true;
		}
		return false;
	}

	public List<Bank> addBank(int userId, long accountNo) {
		User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		Bank bank = bankRepository.findByAccountNo(accountNo).orElseThrow(()->new IllegalArgumentException("Invalid Account No..."));
		user.setAccounts(bank);
		userRepository.save(user);
		return user.getAccounts();
	}

	public boolean addMoney(int userId,long accountNo, double amount) {
		User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		Bank bank = bankRepository.findByAccountNo(accountNo).orElseThrow(()->new IllegalArgumentException("Invalid Account No..."));
			if (user.getAccounts().contains(bank) && bank.getBalance() >= amount) {
				bank.setBalance(bank.getBalance() - amount);
            user.setWalletBalance(user.getWalletBalance() + amount);
            userRepository.save(user);
            bankRepository.save(bank);
            transcationRepository.save(new Transcation(amount, "Wallet", "Credit", java.time.LocalDate.now(), java.time.LocalTime.now(), user));
            transcationRepository.save(new Transcation(amount, bank.getBankName(), "Debit", java.time.LocalDate.now(), java.time.LocalTime.now(), user));
            return true;
            }
		return false;
	}


	
}
