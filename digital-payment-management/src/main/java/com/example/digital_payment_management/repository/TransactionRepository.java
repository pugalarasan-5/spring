package com.example.digital_payment_management.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.digital_payment_management.entity.Transaction;
import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.enums.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	List<Transaction> findByBankName(String name);

	List<Transaction> findByType(TransactionType type);

	List<Transaction> findByDate(LocalDateTime date);

	List<Transaction> findByAmount(BigDecimal amount);

	List<User> findByUser(User user);

}
