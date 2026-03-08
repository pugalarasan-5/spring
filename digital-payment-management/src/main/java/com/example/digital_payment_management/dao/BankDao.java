package com.example.digital_payment_management.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.entity.Transaction;
import com.example.digital_payment_management.enums.BankStatus;
import com.example.digital_payment_management.repository.BankRepository;
import com.example.digital_payment_management.repository.TransactionRepository;

@Repository
public class BankDao {
	@Autowired
	BankRepository bankRepository;

	@Autowired
	TransactionRepository transactionRepository;

	public Bank registerBank(Bank bank) {
		bankRepository.findByEmail(bank.getEmail()).ifPresent(b -> {
			throw new IllegalArgumentException("Email already exists...");
		});
		bankRepository.findByPhone(bank.getPhone()).ifPresent(b -> {
			throw new IllegalArgumentException("Phone number already exists...");
		});
		return bankRepository.save(bank);
	}

	public Bank bankLogin(String email) {
		return bankRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid Email..."));
	}

	public Bank findByAccountNo(long accountNo) {
		return bankRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Account No..."));
	}

	public List<Bank> findByUserName(String userName) {
		return bankRepository.findByUserName(userName);
	}

	public List<Bank> findByBankName(String bankName) {
		return bankRepository.findByBankName(bankName);
	}

	public List<Bank> findByStatus(BankStatus status) {
		return bankRepository.findByStatus(status);
	}

	public Bank findByPhone(String phone) {
		return bankRepository.findByPhone(phone)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Phone Number..."));
	}

	public Bank findByEmail(String email) {
		return bankRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid Email..."));
	}

	public List<Bank> findByCreatedAt(LocalDate date) {
		return bankRepository.findByCreatedAt(date);
	}

	public List<Bank> findAll() {
		return bankRepository.findAll();
	}

	public Bank updateBankStatus(long accountNo, BankStatus status) {
		Bank bank = bankRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new IllegalArgumentException("Invaild Account No...."));
		if (bank.getStatus() == status) {
			throw new IllegalArgumentException("Bank is already " + status);
		}
		bank.setStatus(status);
		return bankRepository.save(bank);
	}

	public Bank updateBankPin(long accountNo, String pin) {
		Bank bank = bankRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new IllegalArgumentException("Invaild Account No...."));
		bank.setPin(pin);
		return bankRepository.save(bank);

	}

	public Bank UpdateBankAddress(long accountNo, String address) {
		Bank bank = bankRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new IllegalArgumentException("Invaild Account No...."));
		bank.setAddress(address);
		return bankRepository.save(bank);
	}

	public boolean sendMoneyBybank(long senderId, long receiverId, BigDecimal amount) {
		Bank sender = bankRepository.findByAccountNo(senderId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Account No..."));
		Bank receiver = bankRepository.findByAccountNo(receiverId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Account No..."));
		if (sender.getBalance().compareTo(amount) >= 0) {
			sender.setBalance(sender.getBalance().subtract(amount));
			receiver.setBalance(receiver.getBalance().add(amount));
			bankRepository.save(sender);
			bankRepository.save(receiver);
			transactionRepository.save(
					new Transaction(amount, sender.getBankName(), "CREDIT", java.time.LocalDateTime.now(), sender.getUser()));
			transactionRepository.save(
					new Transaction(amount, receiver.getBankName(), "DEBIT", java.time.LocalDateTime.now(), receiver.getUser()));
			return true;
		}
		return false;
	}

	public List<Bank> getUserId(int userId) {
		
		return bankRepository.findByUserId(userId);
	}

}
