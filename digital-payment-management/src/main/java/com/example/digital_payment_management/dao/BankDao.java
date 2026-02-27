package com.example.digital_payment_management.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.entity.Transcation;
import com.example.digital_payment_management.repository.BankRepository;
import com.example.digital_payment_management.repository.TranscationRepository;

@Repository
public class BankDao {
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	TranscationRepository transcationRepository;

	public Bank registerBank(Bank bank) {
		bank.setCreateAt(java.time.LocalDate.now());
		return bankRepository.save(bank);
	}

	public Optional<Bank> bankLogin(String email) {
		return bankRepository.findByEmail(email);
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

	public List<Bank> findByStatus(String status) {
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

	public Bank updateBankStatus(long accountNo, String status) {
		Bank bank = bankRepository.findByAccountNo(accountNo)
				.orElseThrow(() -> new IllegalArgumentException("Invaild Account No...."));
		if (bank.getStatus().equalsIgnoreCase(status)) {
			throw new IllegalArgumentException("Bank is already " + status);
		}
		bank.setStatus(status);
		return bankRepository.save(bank);
	}

	public Bank updateBankPin(long accountNo, int pin) {
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

	public boolean sendMoneyBybank(long senderId, long receiverId, double amount) {
		Bank sender = bankRepository.findByAccountNo(senderId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Account No..."));
		Bank receiver = bankRepository.findByAccountNo(receiverId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Account No..."));
		if (sender.getBalance() >= amount) {
			sender.setBalance(sender.getBalance() - amount);
			receiver.setBalance(receiver.getBalance() + amount);
			bankRepository.save(sender);
			bankRepository.save(receiver);
			transcationRepository.save(new Transcation(amount, sender.getBankName(), "Debit", java.time.LocalDate.now(), java.time.LocalTime.now(), null ));
			transcationRepository.save(new Transcation(amount, receiver.getBankName(), "Credit", java.time.LocalDate.now(), java.time.LocalTime.now(), null));
		}
		return false;
	}

}
