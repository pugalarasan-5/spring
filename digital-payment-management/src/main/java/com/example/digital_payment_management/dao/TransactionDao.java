package com.example.digital_payment_management.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.digital_payment_management.entity.Transaction;

import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.enums.TransactionType;
import com.example.digital_payment_management.repository.TransactionRepository;
import com.example.digital_payment_management.repository.UserRepository;

@Repository
public class TransactionDao {
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	UserRepository userRepository;

	public Transaction registerTranscation(Transaction transcation) {
		return transactionRepository.save(transcation);
		
	}
	
	public List<Transaction> findByamount(BigDecimal amount) {
		return transactionRepository.findByAmount(amount);
				
	}

	public List<Transaction> findByBankName(String name) {
		return transactionRepository.findByBankName(name);
				 
	}

	public List<Transaction> findByType(TransactionType type) {
		return transactionRepository.findByType(type);
	}

	public List<Transaction> findByDate(LocalDateTime date) {
		return transactionRepository.findByDate(date);		
	}

//	public List<Transaction> findByUser(int id) {
//		User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user Id.."));
//		List<Transaction> users= transactionRepository.findByUser(user);
//		return users;
//	}

	
}
