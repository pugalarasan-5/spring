package com.example.digital_payment_management.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.entity.Transaction;
import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.enums.UserStatus;
import com.example.digital_payment_management.repository.BankRepository;
import com.example.digital_payment_management.repository.TransactionRepository;
import com.example.digital_payment_management.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
//	@Autowired
	User user;
	
	public User registerUser(User user) {
		Optional<User> email = userRepository.findByEmail(user.getEmail());
		Optional<User> phone = userRepository.findByPhone(user.getPhone());
		if (email.isEmpty() && phone.isEmpty()) {
			user.setStatus(UserStatus.ACTIVE);
			return userRepository.save(user);
		} else {
			throw new IllegalArgumentException("Email or Phone Number Already Exists...");
		}
	}
	
	public Optional<User> userLogin(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
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

	public List<User> findByStatus(UserStatus status) {
		return userRepository.findByStatus(status);
	}
	
	public List<User> findByDate(LocalDate date){
		return userRepository.findByCreatedAt(date);
	}


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

	public User updateUserStatus(int id, UserStatus status) {
		User update = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		if (update.getStatus() == status) {
			throw new IllegalArgumentException("User is already " + status);
		}
		
		update.setStatus(status);
		return userRepository.save(update);
	}

	
	
	
	
	public boolean sendMoney(int senderId, int receiverId, BigDecimal amount) {
		User sender = userRepository.findById(senderId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Sender Id..."));
		User receiver = userRepository.findById(receiverId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Receiver Id..."));
		if (sender.getWalletBalance().compareTo(amount) >= 0) {
			sender.setWalletBalance(sender.getWalletBalance().subtract(amount));
			receiver.setWalletBalance(receiver.getWalletBalance().add(amount));
			userRepository.save(sender);
			userRepository.save(receiver);
			transactionRepository.save(new Transaction(amount, "Wallet", "DEBIT", java.time.LocalDateTime.now(), sender));
			transactionRepository.save(new Transaction(amount, "Wallet", "CREDIT", java.time.LocalDateTime.now(), receiver));
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

	public boolean addMoney(int userId,long accountNo, BigDecimal amount) {
		User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Invalid User Id..."));
		Bank bank = bankRepository.findByAccountNo(accountNo).orElseThrow(()->new IllegalArgumentException("Invalid Account No..."));
		if (bank.getBalance().compareTo(amount) >= 0) {
			bank.setBalance(bank.getBalance().subtract(amount));
			user.setWalletBalance(user.getWalletBalance().add(amount));
			userRepository.save(user);
            bankRepository.save(bank);
            
			transactionRepository.save(new Transaction(amount, "Wallet", "CREDIT", java.time.LocalDateTime.now(), user));
			transactionRepository.save(new Transaction(amount, bank.getBankName(), "DEBIT", java.time.LocalDateTime.now(), user));
			return true;
		}
		return false;
	}


	
}
